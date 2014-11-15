package com.flyn.hastesql.optional;

import java.util.Date;

/**
 * Created by flyn on 2014-11-11.
 */
public enum Type
{
    TEXT(0), BOOLEAN(1), DOUBLE(2), INTEGER(3), DATE(4), BLOB(5), NULL(-1);

    public static final int TYPE_TEXT = 0;
    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_DOUBLE = 2;
    public static final int TYPE_INTEGER = 3;
    public static final int TYPE_DATE = 4;
    public static final int TYPE_BLOB = 5;
    public static final int TYPE_NULL = -1;

    private int type;

    Type(int type)
    {
        this.type = type;
    }


    public int value()
    {
        return type;
    }

    public static int wrap(Class<?> clz)
    {
        if (clz.equals(String.class) || clz.equals(CharSequence.class) || clz.equals(Byte.class) || clz.equals(byte.class) || clz.equals(char
                .class) ||
                clz.equals(Character.class))
        {
            return TYPE_TEXT;
        } else if (clz.equals(boolean.class) || clz.equals(Boolean.class))
        {
            return TYPE_BOOLEAN;
        } else if (clz.equals(double.class) || clz.equals(Double.class) || clz.equals(Float.class) || clz.equals(float.class))
        {
            return TYPE_DOUBLE;
        } else if (clz.equals(int.class) || clz.equals(Integer.class) || clz.equals(long.class) || clz.equals(Long.class) || clz.equals(short
                .class) || clz.equals(Short.class))
        {
            return TYPE_INTEGER;
        } else if (clz.equals(Date.class))
        {
            return TYPE_DATE;
        } else if (clz.isArray() && (clz.getComponentType().equals(byte.class)))
        {
            return TYPE_BLOB;
        }
        return TYPE_NULL;
    }

    public static boolean isUseFul(int value)
    {
        return value >= 0 && value <= 5;
    }
}
