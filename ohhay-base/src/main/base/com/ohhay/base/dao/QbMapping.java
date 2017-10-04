package com.ohhay.base.dao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.sql.Types;

/**
 * @author ThoaiNH
 * create 13/07/2014
 * Annotation write description mapping attribute
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface QbMapping {
    String name() default "";
    int typeMapping() default Types.NULL;
    boolean format() default false;
    String pattern() default "dd-MM-yyyy";
}
