package com.flyn.hastesql.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by flyn on 2014-11-18.
 */
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface Constraint
{

    boolean notNull() default false;

    boolean unique() default false;

    String check() default "";
}
