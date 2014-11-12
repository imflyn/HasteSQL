package com.flyn.hastesql.optional;

/**
 * Created by flyn on 2014-11-11.
 */
public class Property
{
    protected String name;
    protected String type;
    protected Object value;


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
}
