package com.ceam.common.annotations;

/**
 * @author CeaM
 * @Description 数据权限过滤注解
 * @date 2020/10/9 15:15
 */

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope
{
    /**
     * 部门表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";
}
