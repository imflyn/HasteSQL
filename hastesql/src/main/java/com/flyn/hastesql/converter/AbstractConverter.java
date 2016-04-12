package com.flyn.hastesql.converter;

import android.database.Cursor;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public abstract class AbstractConverter
{
    protected final Field  field;
    protected       String type;

    protected AbstractConverter(Field field)
    {
        this.field = field;
        this.field.setAccessible(true);
    }

    public abstract Object getValue(Object obj) throws IllegalAccessException;

    public abstract void setValue(Object value, Object obj) throws IllegalAccessException;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Field getField()
    {
        return field;
    }

    public abstract Object getCursorValueAt(Cursor cursor, int index);
}
