package com.flyn.hastesql.converter;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-12-01.
 */
public class ByteObjectArrayConverter extends AbstractConverter
{

    protected ByteObjectArrayConverter(Field field)
    {
        super(field);
    }

    @Override
    public Object getValue(Object obj) throws IllegalAccessException
    {
        Byte[] bytes = (Byte[]) field.get(obj);
        byte[] bytesArray = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
        {
            bytesArray[i] = bytes[i];
        }
        return bytesArray;
    }

    @Override
    public void setValue(Object value, Object obj) throws IllegalAccessException
    {
        byte[] bytes = (byte[]) value;
        Byte[] bytesArray = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++)
        {
            bytesArray[i] = bytes[i];
        }
        field.set(obj, bytesArray);
    }

}
