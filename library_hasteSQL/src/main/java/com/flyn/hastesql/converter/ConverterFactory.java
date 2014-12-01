package com.flyn.hastesql.converter;

import java.util.Date;

/**
 * Created by flyn on 2014-12-01.
 */
public class ConverterFactory
{

    public static AbstractConverter getConverter(Class<?> clz)
    {
        AbstractConverter abstractConverter = null;
        if (clz.equals(String.class))
        {
            return new StringConverter();
        } else if (clz.equals(CharSequence.class))
        {
            return new CharSequenceConverter();
        } else if (clz.equals(Byte.class))
        {
            return new ByteObjectConverter();
        } else if (clz.equals(byte.class))
        {
            return new ByteConverter();
        } else if (clz.equals(Character.class))
        {
            return new CharacterCharacter();
        } else if (clz.equals(char.class))
        {
            return new CharConverter();
        } else if (clz.equals(Boolean.class))
        {
            return new BooleanObjectConverter();
        } else if (clz.equals(boolean.class))
        {
            return new BooleanConverter();
        } else if (clz.equals(Double.class))
        {
            return new DoubleObjectConverter();
        } else if (clz.equals(double.class))
        {
            return new DoubleConverter();
        } else if (clz.equals(Float.class))
        {
            return new FloatObjectConverter();
        } else if (clz.equals(float.class))
        {
            return new FloatConverter();
        } else if (clz.equals(Integer.class))
        {
            return new IntegerConverter();
        } else if (clz.equals(int.class))
        {
            return new IntConverter();
        } else if (clz.equals(Long.class))
        {
            return new LongObjectConverter();
        } else if (clz.equals(long.class))
        {
            return new LongConverter();
        } else if (clz.equals(Short.class))
        {
            return new ShortObjectConverter();
        } else if (clz.equals(short.class))
        {
            return new ShortConverter();
        } else if (clz.equals(Date.class))
        {
            return new DateConverter();
        } else if (clz.equals(Byte[].class))
        {
            return new ByteObjectArrayConverter();
        } else if (clz.equals(byte[].class))
        {
            return new ByteArrayConverter();
        } else
        {
            throw new IllegalArgumentException("Illegal Class type.");
        }
    }


}
