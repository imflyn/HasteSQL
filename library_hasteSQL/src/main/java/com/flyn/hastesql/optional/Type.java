package com.flyn.hastesql.optional;

import java.util.Date;

/**
 * Created by flyn on 2014-11-11.
 */
public enum Type
{
    BOOLEAN("BOOLEAN"), TEXT("TEXT"), DOUBLE("DOUBLE"), INTEGER("INTEGER"), DATE("DATE"), BLOB("BLOB");

    private String type;

    Type(String type)
    {
        this.type = type;
    }


    String value()
    {
        return type;
    }


    public static String wrap(Class<?> clz)
    {
        if (clz.equals(String.class) || clz.equals(CharSequence.class) || clz.equals(Byte.class) || clz.equals(byte.class) || clz.equals(char
                .class) ||
                clz.equals(Character.class))
        {
            return Type.TEXT.value();
        } else if (clz.equals(boolean.class) || clz.equals(Boolean.class))
        {
            return Type.BOOLEAN.value();
        } else if (clz.equals(boolean.class) || clz.equals(Boolean.class) || clz.equals(Float.class) || clz.equals(float.class))
        {
            return Type.DOUBLE.value();
        } else if (clz.equals(int.class) || clz.equals(Integer.class))
        {
            return Type.INTEGER.value();
        } else if (clz.equals(Date.class))
        {
            return Type.DATE.value();
        } else if (clz.isArray() && (clz.getComponentType().equals(byte.class) || clz.getComponentType().equals(Byte.class)))
        {
            return Type.BLOB.value();
        }
        return Type.TEXT.value();
    }
}
