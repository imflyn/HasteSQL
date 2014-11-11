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
    private final HasteTable hasteTable;

    protected HasteDao(SQLiteDatabase db, String tableName, Class<? extends HasteModel> hasteModelClz)
    {
        this.hasteModelClz = hasteModelClz;
        this.tableName = tableName;
        this.db = db;
        this.hasteTable = new HasteTable(db, tableName, hasteModelClz);
    }

    @Override
    public void insert(HasteModel hasteModel)
    {
        hasteTable.insert(hasteModel);
    }


    @Override
    public void update(HasteModel hasteModel)
    {
        hasteTable.update(hasteModel);
    }


    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {
        hasteTable.insertOrReplace(hasteModel);
    }


    @Override
    public void delete(HasteModel hasteModel)
    {
        hasteTable.delete(hasteModel);
    }


    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz)
    {
        return hasteTable.queryFirst(clz);
    }


    @Override
    public <T extends HasteModel> List<T> queryAll(Class<T> clz)
    {
        return hasteTable.queryAll(clz);
    }


}
