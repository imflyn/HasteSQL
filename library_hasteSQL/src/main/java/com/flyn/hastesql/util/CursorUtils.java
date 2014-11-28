package com.flyn.hastesql.util;

import android.database.Cursor;

import com.flyn.hastesql.core.HasteModel;
import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.optional.Type;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
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
                                                                                                InstantiationException, NoSuchMethodException
    {
        List<T> entities = new ArrayList<T>();
        Constructor<T> constructor = clz.getDeclaredConstructor();
        constructor.setAccessible(true);
        int[] columnIndexArray = new int[properties.length];
        for (int i = 0; i < properties.length; i++)
        {
            columnIndexArray[i] = cursor.getColumnIndex(properties[i].getName());
        }
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            T entity = constructor.newInstance();
            for (int i = 0; i < columnIndexArray.length; i++)
            {
                Field field = properties[i].getField();
                Object value = getCursorValueAt(cursor, i, properties[i].getType());

                ReflectUtils.setFieldValue(field, entity, value);
            }
            entities.add(entity);
        }

        return entities;
    }

    public static Object getCursorValueAt(Cursor cursor, int index, String type)
    {
        if (type.equals(Type.TEXT.value()))
        {
            return cursor.getString(index);
        } else if (type.equals(Type.INTEGER.value()))
        {
            return cursor.getInt(index);
        } else if (type.equals(Type.BOOLEAN.value()))
        {
            return cursor.getInt(index) == 1;
        } else if (type.equals(Type.DOUBLE.value()))
        {
            return cursor.getDouble(index);
        } else if (type.equals(Type.DATE.value()))
        {
            return cursor.getString(index);
        } else if (type.equals(Type.BLOB.value()))
        {
            return cursor.getBlob(index);
        } else
        {
            return null;
        }
    }
}
