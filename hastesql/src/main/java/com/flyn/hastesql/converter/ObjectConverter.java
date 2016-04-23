package com.flyn.hastesql.converter;

import android.database.Cursor;

import com.google.gson.Gson;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2016-04-22.
 */
public class ObjectConverter extends AbstractConverter
{

    public ObjectConverter(Field field)
    {
        super(field);
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        Gson gson = new Gson();
        return gson.toJson(field.get(obj));
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        Gson gson = new Gson();
        Object realValue = gson.fromJson(value.toString(), field.getType());
        field.set(obj, realValue);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        return cursor.getString(index);
    }

}
