package com.flyn.hastesql.core;

import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.util.ReflectUtils;

/**
 * Created by flyn on 2014-11-11.
 */
class HasteTable
{

    private final String     tableName;
    private       Property   primaryKey;
    private       Property[] allColumns;

    protected HasteTable(String tableName, Class<? extends HasteModel> clz)
    {
        this.tableName = tableName;
        this.allColumns = ReflectUtils.getPropertyArray(clz);
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
