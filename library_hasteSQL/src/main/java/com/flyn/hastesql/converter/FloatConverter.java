package com.flyn.hastesql.converter;

import android.database.Cursor;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public class FloatConverter extends AbstractConverter
{
    protected FloatConverter(Field field)
    {
        super(field);
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        return field.getFloat(obj);
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        field.setFloat(obj, (Float) value);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        return cursor.getFloat(index);
    }

}
