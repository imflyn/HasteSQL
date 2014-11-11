package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 * <p/>
 * Not Thread-Safe
 */
public class HasteTable implements HasteOperation
{

    private final SQLiteDatabase db;
    private final String tableName;
    private final Class<? extends HasteModel> hasteModelClz;

    protected HasteTable(SQLiteDatabase db, String tableName, Class<? extends HasteModel> hasteModelClz)
    {
        this.hasteModelClz = hasteModelClz;
        this.tableName = tableName;
        this.db = db;
    }

    private void checkIfExits()
    {
        //TODO 检查是否存在该表,没有则建表
    }


    @Override
    public void insert(HasteModel hasteModel)
    {


    }


    @Override
    public void update(HasteModel hasteModel)
    {

    }


    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {

    }


    @Override
    public void delete(HasteModel hasteModel)
    {

    }

    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz)
    {
        return null;
    }


    @Override
    public <T extends HasteModel> List<T> queryAll(Class<T> clz)
    {
        return null;
    }

}
