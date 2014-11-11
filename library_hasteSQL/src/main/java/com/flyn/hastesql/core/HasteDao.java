package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Not Thread-Safe
 */
public class HasteDao implements HasteOperation
{
    private final SQLiteDatabase db;
    private final String tableName;
    private final Class<? extends HasteModel> hasteModelClz;

    protected HasteDao(SQLiteDatabase db, String tableName, Class<? extends HasteModel> hasteModelClz)
    {
        this.hasteModelClz = hasteModelClz;
        this.tableName = tableName;
        this.db = db;
    }

    public String getTableName()
    {
        return tableName;
    }

    public Class<? extends HasteModel> getHasteModelClz()
    {
        return hasteModelClz;
    }

    @Override
    public void insert(HasteModel hasteModel)
    {

    }

    @Override
    public void insert(HasteModel hasteModel, String prefix, String suffix)
    {

    }


    @Override
    public void update(HasteModel hasteModel)
    {

    }

    @Override
    public void update(HasteModel hasteModel, String prefix, String suffix)
    {

    }


    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {

    }

    @Override
    public void insertOrReplace(HasteModel hasteModel, String prefix, String suffix)
    {

    }

    @Override
    public void delete(HasteModel hasteModel)
    {

    }

    @Override
    public void delete(HasteModel hasteModel, String prefix, String suffix)
    {

    }


    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz)
    {
        return null;
    }

    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz, String prefix, String suffix)
    {
        return null;
    }


    @Override
    public <T extends HasteModel> List<T> queryAll(Class<T> clz)
    {
        return null;
    }

    @Override
    public <T extends HasteModel> List<T> queryAll(Class<T> clz, String prefix, String suffix)
    {
        return null;
    }

}
