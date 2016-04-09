package com.flyn.hastesql.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flyn.hastesql.converter.AbstractConverter;
import com.flyn.hastesql.core.HasteModel;
import com.flyn.hastesql.optional.Property;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
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
        Object value;
        AbstractConverter abstractConverter;
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
        {
            entity = constructor.newInstance();
            for (int i = 0; i < columnIndexArray.length; i++)
            {
                abstractConverter = properties[i].getConverter();
                value = abstractConverter.getCursorValueAt(cursor, i);
                abstractConverter.setValue(value, entity);
            }
            entities.add(entity);

            if (onlyFirst)
            {
                break;
            }
        }

        return entities;
    }

    public static boolean checkColumnExist(SQLiteDatabase db, String tableName, String columnName)
    {
        Cursor cursor = null;
        try
        {
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0", null);
            return cursor != null && cursor.getColumnIndex(columnName) != -1;
        } catch (Exception e)
        {
            LogUtils.e(e);
            throw new RuntimeException(e);
        } finally
        {
            closeQuietly(cursor);
        }

    }

    public static boolean checkTableExist(Cursor cursor)
    {
        if (cursor != null)
        {
            try
            {
                if (cursor.moveToNext())
                {
                    int count = cursor.getInt(0);
                    if (count > 0)
                    {
                        return true;
                    }
                }
            } finally
            {
                CursorUtils.closeQuietly(cursor);
            }
        }

        return false;
    }


}
