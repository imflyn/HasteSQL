package com.flyn.hastesql.converter;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public class ShortConverter extends AbstractConverter
{
    protected ShortConverter(Field field)
    {
        super(field);
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        return field.getShort(obj);
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        field.set(obj, value);
    }

}