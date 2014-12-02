package com.flyn.hastesql.util;

import android.database.Cursor;

import com.flyn.hastesql.core.HasteModel;
import com.flyn.hastesql.optional.Property;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 */
public class CursorUtils
{

    public static void closeQuietly(Closeable closeable)
    {
        if (null != closeable)
        {
            try
            {
                closeable.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static <T extends HasteModel> List<T> cursorToEntities(Class<T> clz, Cursor cursor,
                                                                  Property[] properties) throws IllegalAccessException, InvocationTargetException,
                                                                                                InstantiationException, NoSuchMethodException,
                                                                                                ParseException
    {
        return cursorToEntities(clz, cursor, properties, false);
    }

    public static <T extends HasteModel> List<T> cursorToEntities(Class<T> clz, Cursor cursor, Property[] properties,
                                                                  boolean onlyFirst) throws IllegalAccessException, InvocationTargetException,
                                                                                            InstantiationException, NoSuchMethodException,
                                                                                            ParseException
    {
        List<T> entities = new ArrayList<T>();
        Constructor<T> constructor = clz.getDeclaredConstructor();
        constructor.setAccessible(true);
        int[] columnIndexArray = new int[properties.length];
        for (int i = 0; i < properties.length; i++)
        {
            columnIndexArray[i] = cursor.getColumnIndex(properties[i].getName());
        }
        T entity;
        Field field;
        Object value;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            entity = constructor.newInstance();
            for (int i = 0; i < columnIndexArray.length; i++)
            {
                field = properties[i].getConverter().getField();
                value = getCursorValueAt(cursor, i, field);
                properties[i].getConverter().setValue(value, entity);
            }
            entities.add(entity);

            if (onlyFirst)
            {
                break;
            }
        }

        return entities;
    }

    public static Object getCursorValueAt(Cursor cursor, int index, Field field) throws ParseException
    {
        Class<?> clz = field.getType();
        if (clz.equals(String.class))
        {
            return cursor.getString(index);
        } else if (clz.equals(CharSequence.class))
        {
            return cursor.getString(index);
        } else if (clz.equals(Byte.class) || clz.equals(byte.class))
        {
            return cursor.getString(index).getBytes()[0];
        } else if (clz.equals(char.class) || clz.equals(Character.class))
        {
            return cursor.getString(index).charAt(0);
        } else if (clz.equals(boolean.class) || clz.equals(Boolean.class))
        {
            return cursor.getInt(index) == 1;
        } else if (clz.equals(double.class) || clz.equals(Double.class))
        {
            return cursor.getDouble(index);
        } else if (clz.equals(float.class) || clz.equals(Float.class))
        {
            return cursor.getFloat(index);
        } else if (clz.equals(int.class) || clz.equals(Integer.class))
        {
            return cursor.getInt(index);
        } else if (clz.equals(long.class) || clz.equals(Long.class))
        {
            return cursor.getLong(index);
        } else if (clz.equals(short.class) || clz.equals(Short.class))
        {
            return cursor.getShort(index);
        } else if (clz.equals(Date.class))
        {
            return cursor.getLong(index);
        } else if (clz.equals(byte[].class) || clz.equals(Byte[].class))
        {
            return cursor.getBlob(index);
        } else
        {
            return null;
        }
    }
}
