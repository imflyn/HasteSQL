package com.flyn.hastesql.core;

/**
 * Created by flyn on 2014-11-11.
 */
public class HasteTable
{

    private String tableName;
    private  String[] allColumns;

    protected HasteTable(String tableName, Class<? extends HasteModel> clz)
    {
        this.tableName=tableName;
        init(clz);
    }

    private void init(Class<? extends HasteModel> clz)
    {



    }

    protected  String createTable()
    {
return  "";
    }
}
