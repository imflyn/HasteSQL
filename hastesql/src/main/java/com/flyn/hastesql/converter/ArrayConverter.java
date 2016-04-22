package com.flyn.hastesql.converter;

import android.database.Cursor;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by flyn on 2016-04-22.
 */
public class ArrayConverter extends AbstractConverter
{
    private Class genericClz;

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
        Gson gson = new Gson();
        Object realValue = gson.fromJson(value.toString(),  Array.newInstance(genericClz, 0).getClass());
        field.set(obj, realValue);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        return cursor.getString(index);
    }

    class ArrayJsonType implements ParameterizedType
    {
        private Class<?> wrapped;

        public ArrayJsonType(Class<?> wrapper)
        {
            this.wrapped = wrapper;
        }

        @Override
        public Type[] getActualTypeArguments()
        {
            return new Type[]{wrapped};
        }

        @Override
        public Type getRawType()
        {
            return Array.newInstance(genericClz, 0).getClass();
        }

        @Override
        public Type getOwnerType()
        {
            return null;
        }
    }
}
