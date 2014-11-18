package com.flyn.hastesql.core;

import com.flyn.hastesql.util.ReflectUtils;
import com.flyn.hastesql.util.SQLUtils;

import java.util.List;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Not Thread-Safe
 */
public class HasteDao implements HasteOperation
{
    private final HasteTable hasteTable;
    private final SQLExecutor sqlExecutor;

    protected HasteDao(SQLExecutor sqlExecutor, String tableName, Class<? extends HasteModel> hasteModelClz)
    {
        this.sqlExecutor = sqlExecutor;
        createTableIfNotExits(tableName, hasteModelClz);
        this.hasteTable = new HasteTable(tableName, hasteModelClz);
    }

    protected void createTableIfNotExits(String tableName, Class<? extends HasteModel> hasteModelClz)
    {

        String checkTableSQL = SQLUtils.createSQLCheckTableExits(tableName);
        if (sqlExecutor.isEmpty(checkTableSQL))
        {
            //如果为空则建表
            String createTableSQL = SQLUtils.createSQLCreateTable(tableName, ReflectUtils.getPropertyArray(hasteModelClz));
            sqlExecutor.execSQL(createTableSQL);
        }
    }

    @Override
    public void insert(HasteModel hasteModel)
    {
        sqlExecutor.insert(hasteTable.getTableName(), hasteTable.getAllColumns(), hasteModel);

    }

    @Override
    public void insertAll(List<? extends HasteModel> hasteModelList)
    {
        sqlExecutor.insertAll(hasteTable.getTableName(), hasteTable.getAllColumns(), hasteModelList);
    }


    @Override
    public void update(HasteModel hasteModel)
    {

    }


    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {
        sqlExecutor.insertOrReplace(hasteTable.getTableName(), hasteTable.getAllColumns(), hasteModel);
    }

    @Override
    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList)
    {
        sqlExecutor.insertOrReplaceAll(hasteTable.getTableName(), hasteTable.getAllColumns(), hasteModelList);
    }


    @Override
    public void delete(HasteModel hasteModel)
    {
    }

    @Override
    public void deleteAll(Class<? extends HasteModel> clz)
    {
        sqlExecutor.deleteAll(hasteTable.getTableName());
    }

    @Override
    public void deleteAll(List<? extends HasteModel> hasteModelList)
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
