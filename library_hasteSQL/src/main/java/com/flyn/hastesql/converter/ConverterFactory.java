package com.flyn.hastesql.converter;

import com.flyn.hastesql.optional.Type;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by flyn on 2014-12-01.
 */
public class ConverterFactory
{

    public static AbstractConverter getConverter(Field field)
    {
        Class<?> clz = field.getType();
        String type = Type.wrap(clz);

        AbstractConverter abstractConverter = null;
        if (clz.equals(String.class))
        {
            abstractConverter = new StringConverter(field);
        } else if (clz.equals(CharSequence.class))
        {
            abstractConverter = new CharSequenceConverter(field);
        } else if (clz.equals(Byte.class))
        {
            abstractConverter = new ByteObjectConverter(field);
        } else if (clz.equals(byte.class))
        {
            abstractConverter = new ByteConverter(field);
        } else if (clz.equals(Character.class))
        {
            abstractConverter = new CharacterCharacter(field);
        } else if (clz.equals(char.class))
        {
            abstractConverter = new CharConverter(field);
        } else if (clz.equals(Boolean.class))
        {
            abstractConverter = new BooleanObjectConverter(field);
        } else if (clz.equals(boolean.class))
        {
            abstractConverter = new BooleanConverter(field);
        } else if (clz.equals(Double.class))
        {
            abstractConverter = new DoubleObjectConverter(field);
        } else if (clz.equals(double.class))
        {
            abstractConverter = new DoubleConverter(field);
        } else if (clz.equals(Float.class))
        {
            abstractConverter = new FloatObjectConverter(field);
        } else if (clz.equals(float.class))
        {
            abstractConverter = new FloatConverter(field);
        } else if (clz.equals(Integer.class))
        {
            abstractConverter = new IntegerConverter(field);
        } else if (clz.equals(int.class))
        {
            abstractConverter = new IntConverter(field);
        } else if (clz.equals(Long.class))
        {
            abstractConverter = new LongObjectConverter(field);
        } else if (clz.equals(long.class))
        {
            abstractConverter = new LongConverter(field);
        } else if (clz.equals(Short.class))
        {
            abstractConverter = new ShortObjectConverter(field);
        } else if (clz.equals(short.class))
        {
            abstractConverter = new ShortConverter(field);
        } else if (clz.equals(Date.class))
        {
            abstractConverter = new DateConverter(field);
        } else if (clz.equals(Byte[].class))
        {
            abstractConverter = new ByteObjectArrayConverter(field);
        } else if (clz.equals(byte[].class))
        {
            abstractConverter = new ByteArrayConverter(field);
        } else
        {
            return null;
        }
        abstractConverter.setType(type);
        return abstractConverter;
    }


}
