package com.flyn.hastesql.converter;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by flyn on 2014-12-01.
 */
public class DateConverter extends AbstractConverter
{
    protected DateConverter(Field field)
    {
        super(field);
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        long timeStamp = ((Date) field.get(obj)).getTime();
        return timeStamp;
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        long timeStamp = (Long) value;
        Date date = new Date(timeStamp);
        field.set(obj, date);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        return cursor.getLong(index);
    }

}
