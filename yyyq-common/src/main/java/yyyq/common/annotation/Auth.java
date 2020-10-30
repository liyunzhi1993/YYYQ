package yyyq.common.annotation;

import java.lang.annotation.*;


/**
 * 认证
 *
 * @author liyunzhi
 * @date 2018/4/26 13:59
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
@Documented
public @interface Auth {
}
