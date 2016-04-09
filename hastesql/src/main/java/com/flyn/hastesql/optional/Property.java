package com.flyn.hastesql.optional;

import com.flyn.hastesql.converter.AbstractConverter;

/**
 * Created by flyn on 2014-11-11.
 */
public class Property
{
    private String name;
    private boolean isPrimaryKey;
    private boolean isAutoIncrease;
    private boolean notNull;
    private boolean unique;
    private String check;
    private AbstractConverter converter;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean isPrimaryKey()
    {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean isPrimaryKey)
    {
        this.isPrimaryKey = isPrimaryKey;
    }

    public boolean isAutoIncrease()
    {
        return isAutoIncrease;
    }

    public void setAutoIncrease(boolean isAutoIncrease)
    {
        this.isAutoIncrease = isAutoIncrease;
    }

    public boolean isNotNull()
    {
        return notNull;
    }

    public void setNotNull(boolean notNull)
    {
        this.notNull = notNull;
    }

    public boolean isUnique()
    {
        return unique;
    }

    public void setUnique(boolean unique)
    {
        this.unique = unique;
    }

    public String getCheck()
    {
        return check;
    }

    public void setCheck(String check)
    {
        this.check = check;
    }

    public AbstractConverter getConverter()
    {
        return converter;
    }

    public void setConverter(AbstractConverter converter)
    {
        this.converter = converter;
    }
}
