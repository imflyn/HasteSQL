package com.flyn.hastesql.optional;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 */
public enum Type
{
    BOOLEAN("BOOLEAN"), TEXT("TEXT"), DOUBLE("DOUBLE"), INTEGER("INTEGER"), BLOB("BLOB");

    private String type;

    Type(String type)
    {
        this.type = type;
    }

    public static String wrap(Class<?> clz)
    {
        if (clz.equals(String.class) || clz.equals(CharSequence.class) || clz.equals(Byte.class) || clz.equals(byte.class) || clz.equals(char
                .class) || clz.equals(Character.class) || clz.equals(List.class) || clz.equals(Array.class))
        {
            return Type.TEXT.value();
        } else if (clz.equals(boolean.class) || clz.equals(Boolean.class))
        {
            return Type.BOOLEAN.value();
        } else if (clz.equals(double.class) || clz.equals(Double.class) || clz.equals(Float.class) || clz.equals(float.class))
        {
            return Type.DOUBLE.value();
        } else if (clz.equals(int.class) || clz.equals(Integer.class) || clz.equals(long.class) || clz.equals(Long.class) || clz.equals(short
                .class) || clz.equals(Short.class))
        {
            return Type.INTEGER.value();
        } else if (clz.equals(Date.class))
        {
            return Type.INTEGER.value();
        } else if (clz.equals(byte[].class) || clz.equals(Byte[].class))
        {
            return Type.BLOB.value();
        }
        return null;
    }

    public String value()
    {
        return type;
    }
}
