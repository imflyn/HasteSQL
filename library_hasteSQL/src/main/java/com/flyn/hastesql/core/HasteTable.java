package com.flyn.hastesql.core;

import com.flyn.hastesql.optional.Property;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Created by flyn on 2014-11-11.
 */
public class HasteTable
{

    private String tableName;
    private Property[] allColumns;
    private Property[] pkColumns;


    protected HasteTable(String tableName, Class<? extends HasteModel> clz)
    {
        this.tableName = tableName;
        init(clz);
    }

    private void init(Class<? extends HasteModel> clz)
    {

    }

    protected String createTable()
    {
        return "";
    }
}
