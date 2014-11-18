package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteStatement;

import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.util.ReflectUtils;

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
            if (property.isPrimaryKey() || property.getName().equalsIgnoreCase("id") || property.getName().equalsIgnoreCase("_id"))
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

    public Property[] getPkColumns()
    {
        return pkColumns;
    }

    public Property getPrimaryKey()
    {
        return primaryKey;
    }

}
