package com.ceam.common.annotations;

import java.lang.annotation.*;

/**
 * @author CeaM
 * @Description redis删除注解
 * @date 2020/10/9 10:03
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisEvict
{
    String key();

    String fieldKey();
}
