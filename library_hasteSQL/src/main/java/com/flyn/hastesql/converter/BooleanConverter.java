package com.flyn.hastesql.converter;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public class BooleanConverter extends AbstractConverter
{
    protected BooleanConverter(Field field)
    {
        super(field);
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        return field.getBoolean(obj);
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        field.setBoolean(obj, (Boolean) value);
    }


}
