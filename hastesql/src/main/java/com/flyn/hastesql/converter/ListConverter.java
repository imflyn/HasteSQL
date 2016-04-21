package com.flyn.hastesql.converter;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * Created by flyn on 2014-12-01.
 */
public class ListConverter extends AbstractConverter
{
    private Class<?> genericClz;

    public ListConverter(Field field)
    {
        super(field);
        ParameterizedType genericType = (ParameterizedType) field.getGenericType();
        genericClz = (Class<?>) genericType.getActualTypeArguments()[0];
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        GsonBuilder gb = new GsonBuilder();
        gb.serializeNulls();
        Gson gson = gb.create();
        return gson.toJson(field.get(obj));
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        Gson gson = new Gson();
        Object realValue = gson.fromJson(value.toString(), obj.getClass());
        field.set(obj, realValue);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        return cursor.getString(index);
    }


}
