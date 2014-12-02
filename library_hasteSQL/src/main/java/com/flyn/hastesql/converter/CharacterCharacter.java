package com.flyn.hastesql.converter;

import android.database.Cursor;
import android.text.TextUtils;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public class CharacterCharacter extends AbstractConverter
{
    protected CharacterCharacter(Field field)
    {
        super(field);
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        return field.get(obj);
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        field.set(obj, value);
    }

    @Override
    public Object getCursorValueAt(Cursor cursor, int index)
    {
        String str = cursor.getString(index);
        if (TextUtils.isEmpty(str))
        {
            return null;
        } else
        {
            return str.charAt(0);
        }
    }

}
