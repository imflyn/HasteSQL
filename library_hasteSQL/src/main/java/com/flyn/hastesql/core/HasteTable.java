package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteStatement;

import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.util.ReflectUtils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by flyn on 2014-11-11.
 */
public class HasteTable
{

    private final String tableName;
    private Property[] allColumns;
    private Property primaryKey;
    private Property[] pkColumns;
    private SQLiteStatement insertSQLiteStatement;
    protected AtomicInteger sequence = new AtomicInteger();

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
        createSQLGetMaxId
    }


    public String getTableName()
    {
        return tableName;
    }

    public Property[] getAllColumns()
    {
        return allColumns;
    }

    public Property[] getPkColumns()
    {
        return pkColumns;
    }

    public Property getPrimaryKey()
    {
        return primaryKey;
    }

    public boolean hasPrimaryKey()
    {
        return null != primaryKey;
    }
}
