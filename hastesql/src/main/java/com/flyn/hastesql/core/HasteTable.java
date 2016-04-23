package com.flyn.hastesql.core;

import com.flyn.hastesql.optional.Property;

/**
 * Created by flyn on 2014-11-11.
 */
public class HasteTable
{

    private final String     tableName;
    private       Property   primaryKey;
    private       Property[] allColumns;

    protected HasteTable(String tableName, Property[] allColumns)
    {
        this.tableName = tableName;
        this.allColumns = allColumns;
        init();
    }


    private void init()
    {
        for (Property property : allColumns)
        {
            if (property.isPrimaryKey())
            {
                primaryKey = property;
                break;
            }
        }
    }


    public String getTableName()
    {
        return tableName;
    }

    public Property[] getAllColumns()
    {
        return allColumns;
    }

    public Property getPrimaryKey()
    {
        return primaryKey;
    }

    public boolean isAutoIncrease()
    {
        return primaryKey != null && primaryKey.isAutoIncrease();
    }

    public boolean hasPrimaryKey()
    {
        return null != primaryKey;
    }

}
