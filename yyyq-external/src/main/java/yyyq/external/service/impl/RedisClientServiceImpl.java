package yyyq.external.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import yyyq.external.service.RedisClientService;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * RedisClientServiceImpl
 *
 * @author liyunzhi
 * @date 2018/7/29 16:27
 */
@Service
public class RedisClientServiceImpl implements RedisClientService {
    @Autowired
    private RedisTemplate redisTemplate;

    public void setStringToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void setStringToRedis(String key, String value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public String getStringFromRedis(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o == null ? null : o.toString();
    }

    public <T> void setObjectToRedis(String key, T t) {
        redisTemplate.opsForValue().set(key, t);
    }

    public <T> void setObjectToRedis(String key, T t, long time) {
        redisTemplate.opsForValue().set(key, t, time, TimeUnit.SECONDS);
    }

    public <T> T getObjectFromRedis(String key, Class<T> clazz) {
        T t = null;
        t = (T) (redisTemplate.opsForValue().get(key));
        return t;
    }

    public <T> T getAndUpdateObject(String key, Class<T> clazz, long time) {
        T t = null;
        t = (T) (redisTemplate.opsForValue().get(key));
        if (t != null && time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
        return t;
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    public void setHashToRedis(String key, Map<String, Object> value, long time) {
        redisTemplate.opsForHash().putAll(key, value);
        if (time > 0) {
            redisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    public <T> T getHashObjectFromRedis(String key, Class<T> clazz) {
        T t = null;
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        if (map != null && map.size() > 0) {
            String jsonMap = JSON.toJSONString(map);
            t = (T) JSONObject.parseObject(jsonMap, clazz);
        }

        return t;
    }

    public Object getHashObjectFromRedis(String key, String objectKey) {
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        Object object = redisTemplate.opsForHash().get(key, objectKey);
        return object;
    }
}
