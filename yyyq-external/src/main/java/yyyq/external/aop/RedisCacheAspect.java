package yyyq.external.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import yyyq.common.util.StringUtil;
import yyyq.external.annotation.RedisCacheClean;
import yyyq.external.annotation.RedisCacheGet;
import yyyq.external.service.RedisClientService;

import java.lang.reflect.Method;

/**
 * RedisCacheAspect
 *
 * @author liyunzhi
 * @date 2018/7/29 15:54
 */
@Aspect
@Component
@Order(0)
public class RedisCacheAspect {
    private static final int ONEDAY = 60 * 60 * 24; //24h

    @Autowired
    private RedisClientService redisClientService;

    @Around("@annotation(redisCacheGet)")
    public Object cacheGet(ProceedingJoinPoint joinPoint,RedisCacheGet redisCacheGet) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        return goRedisCacheGet(args, method, joinPoint);
    }

    @Around("@annotation(redisCacheClean)")
    public Object cacheClean(ProceedingJoinPoint joinPoint,RedisCacheClean redisCacheClean) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        return goRedisCacheClean(args, method, joinPoint);

    }

    /** * @param args * @param method * @param joinPoint * @return * @throws Throwable * @description redisCacheGet注解解析方法 */
    public Object goRedisCacheGet(Object[] args, Method method, ProceedingJoinPoint joinPoint) throws Throwable {

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();

        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        //如果有这个注解，则获取注解类
        RedisCacheGet methodType = method.getAnnotation(RedisCacheGet.class);
        String methodKey = methodType.key().substring(methodType.key().indexOf("#"),methodType.key().length());
        String key = parser.parseExpression(methodKey).getValue(context, String.class);
        key= methodType.key().substring(0,methodType.key().indexOf(":")+1)+key;
        if (methodType.dataType() != RedisCacheGet.DataType.JSON) {//JSON形式返回
            if (methodType.force()) {//强制更新数据
                Object object = joinPoint.proceed(args);
                if (object != null) {
                    setRedisValueJson(methodType, key, object);
                }
                //返回值类型
                return object;
            } else {
                String json =redisClientService.getStringFromRedis(key);
                if (StringUtil.isNotNullOrEmpty(json)) {
                    try {
                        return json;
                    } catch (Exception e) {
                    }
                    return null;
                } else {//查询数据，缓存，返回对象
                    Object object = joinPoint.proceed(args);
                    if (object != null) {
                        setRedisValueJson(methodType, key, object);
                    }
                    return object;
                }
            }
        } else {//CLASS形式保存
            if (methodType.force()) {//强制更新数据
                Object object = joinPoint.proceed(args);
                if (object != null) {
                    setRedisValueJson(methodType, key, object);
                }
                //返回值类型
                return object;
            } else {
                if (StringUtil.isNotNullOrEmpty(redisClientService.getStringFromRedis(key))) {//对象存在直接返回
                    return JSON.parseObject(redisClientService.getStringFromRedis(key),Class.forName(methodType.key().substring(methodType.key().indexOf("@")+1, methodType.key().indexOf("#"))));
                } else {//查询数据，缓存，返回对象
                    Object object = joinPoint.proceed(args);
                    if (object != null) {
                        setRedisValueJson(methodType, key, object);
                    }
                    return object;
                }
            }
        }
    }

    /** * @param methodType * @param key * @param object */
    private void setRedisValueJson(RedisCacheGet methodType, String key, Object object) {
        String jsonStr = JSON.toJSONString(object);
        if (methodType.expire() == 0) {//0:永不过期
            redisClientService.setStringToRedis(key, jsonStr);
        } else if (methodType.expire() == 1) {//1:过期时间为24h
            redisClientService.setStringToRedis(key, jsonStr,ONEDAY);
        } else {//手动指定
            redisClientService.setStringToRedis(key, jsonStr,methodType.expire());
        }
    }

    /** * @param args * @param method * @param joinPoint * @return * @throws Throwable * @description redisCache 清除方法 */
    public Object goRedisCacheClean(Object[] args, Method method, ProceedingJoinPoint joinPoint) throws Throwable {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        //获取被拦截方法参数名列表(使用Spring支持类库)
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paraNameArr = u.getParameterNames(method);
        //把方法参数放入SPEL上下文中
        for (int i = 0; i < paraNameArr.length; i++) {
            context.setVariable(paraNameArr[i], args[i]);
        }
        //如果有这个注解，则获取注解类
        Object object = joinPoint.proceed(args);

        //如果有这个注解，则获取注解类
        RedisCacheClean methodType = method.getAnnotation(RedisCacheClean.class);
        if(methodType.isBatch()){
            for (String str : methodType.key()) {
                String keyStr = parser.parseExpression(str).getValue(context, String.class);
                String[] keys = keyStr.split(",");
                for (String key : keys) {
                    redisClientService.deleteKey(key);
                }
            }
        } else {
            for (String str : methodType.key()) {
                String key = parser.parseExpression(str).getValue(context, String.class);
                redisClientService.deleteKey(key);
            }
        }
        return object;
    }
}
