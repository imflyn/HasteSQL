package com.flyn.hastesql.optional;

import java.lang.reflect.Field;

/**
 * Created by flyn on 2014-11-11.
 */
public class Property
{
    private String name;
    private String type;
    private Object value;
    private Field field;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }

    public Field getField()
    {
        return field;
    }

    public void setField(Field field)
    {
        this.field = field;
    }
}
