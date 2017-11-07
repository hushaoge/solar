package com.solar.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by hushaoge on 2016/11/25.
 */
@Target(ElementType.FIELD)
public @interface MyField {
    int isRequired() default 0;
    String defaultValue() default "";
}
