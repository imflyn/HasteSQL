package com.flyn.hastesql.converter;

import android.database.Cursor;

import com.google.gson.Gson;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public class ArrayConverter extends AbstractConverter
{
    private Class<?> genericClz;

    public ArrayConverter(Field field)
    {
        super(field);
        genericClz = (Class<?>) field.getGenericType();
    }


    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        Gson gson = new Gson();
        String content = gson.toJson(field.get(obj));
        return content;
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        field.set(obj, value);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        return cursor.getString(index);
    }

}
