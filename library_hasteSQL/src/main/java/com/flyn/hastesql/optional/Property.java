package com.flyn.hastesql.optional;

/**
 * Created by flyn on 2014-11-11.
 */
public class Property
{

    protected String name;
    protected Class<?> type;
    protected boolean isPrimaryKey;
    public boolean isPrimaryKey()
    {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean isPrimaryKey)
    {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Class<?> getType()
    {
        return type;
    }

    public void setType(Class<?> type)
    {
        this.type = type;
    }



}
