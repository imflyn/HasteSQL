package com.flyn.hastesql.converter;

import android.database.Cursor;

import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by flyn on 2016-04-22.
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
        Gson gson = new Gson();
        return gson.toJson(field.get(obj));
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        Gson gson = new Gson();
        Object realValue = gson.fromJson(value.toString(), new ListJsonType(genericClz));
        field.set(obj, realValue);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        return cursor.getString(index);
    }

    class ListJsonType implements ParameterizedType
    {
        private Class<?> wrapped;

        public ListJsonType(Class<?> wrapper)
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
            return List.class;
        }

        @Override
        public Type getOwnerType()
        {
            return null;
        }
    }
}
