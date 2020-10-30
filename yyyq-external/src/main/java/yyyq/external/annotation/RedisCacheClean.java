package yyyq.external.annotation;

import java.lang.annotation.*;

/**
 * RedisCacheClean
 *
 * @author liyunzhi
 * @date 2018/7/29 15:53
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RedisCacheClean {

    /** * key值 * * @return */
    String[] key();

    /** * 是否批量 * * @return */
    boolean isBatch() default false;
}