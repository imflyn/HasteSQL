package com.flyn.hastesql.converter;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public abstract class AbstractConverter
{
    Field field;

    public abstract Object getValue();

    public abstract void setValue();

    public abstract String getType();
}
