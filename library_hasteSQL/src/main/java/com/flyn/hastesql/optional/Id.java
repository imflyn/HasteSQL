package com.flyn.hastesql.optional;

/**
 * Created by flyn on 2014-11-11.
 */
public class Id extends Property
{

    private boolean autoIncrease;

    public boolean isAutoIncrease()
    {
        return autoIncrease;
    }

    public void setAutoIncrease(boolean autoIncrease)
    {
        this.autoIncrease = autoIncrease;
    }

    @Override
    public boolean isPrimaryKey()
    {
        return true;
    }


    @Override
    public Class<?> getType()
    {
        return int.class;
    }

}
