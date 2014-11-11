package com.flyn.hastesql.core;

import java.util.List;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Not Thread-Safe
 */
public class HasteDao implements HasteOperation
{
    private final String tableName;
    private final Class<? extends HasteModel> hasteModelClz;
    private final SQLExecutor sqlExecutor;

    protected HasteDao(SQLExecutor sqlExecutor, String tableName, Class<? extends HasteModel> hasteModelClz)
    {
        this.sqlExecutor = sqlExecutor;
        this.hasteModelClz = hasteModelClz;
        this.tableName = tableName;
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
