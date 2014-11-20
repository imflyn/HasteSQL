package com.flyn.hastesql.core;

import com.flyn.hastesql.optional.ConditionExpression;
import com.flyn.hastesql.util.ReflectUtils;
import com.flyn.hastesql.util.SQLUtils;

import java.util.ArrayList;
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
        String sql = SQLUtils.createSQLInsert(hasteTable.getTableName(), hasteTable.getAllColumns());
        Object[] objects = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, true);
        sqlExecutor.execSQL(sql, objects);
    }

    @Override
    public void insertAll(List<? extends HasteModel> hasteModelList)
    {
        String sql = SQLUtils.createSQLInsert(hasteTable.getTableName(), hasteTable.getAllColumns());
        List<Object[]> objects = new ArrayList<Object[]>(hasteModelList.size());
        for (int i = 0, size = hasteModelList.size(); i < size; i++)
        {
            objects.add(ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModelList.get(i), true));
        }

        sqlExecutor.execSQLList(sql, objects);
    }


    @Override
    public void update(HasteModel hasteModel)
    {

    }


    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {
        String sql = SQLUtils.createSQLInsertOrReplace(hasteTable.getTableName(), hasteTable.getAllColumns());
        Object[] objects = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, true);
        sqlExecutor.execSQL(sql, objects);
    }

    @Override
    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList)
    {
        String sql = SQLUtils.createSQLInsertOrReplace(hasteTable.getTableName(), hasteTable.getAllColumns());
        List<Object[]> objects = new ArrayList<Object[]>(hasteModelList.size());
        for (int i = 0, size = hasteModelList.size(); i < size; i++)
        {
            objects.add(ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModelList.get(i), true));
        }
        sqlExecutor.execSQLList(sql, objects);
    }


    @Override
    public void delete(HasteModel hasteModel)
    {
    }

    @Override
    public void delete(HasteModel hasteModel, ConditionExpression conditionExpression)
    {

    }

    @Override
    public void deleteAll(Class<? extends HasteModel> clz)
    {
        String sql = SQLUtils.createSQLDeleteAll(hasteTable.getTableName());
        sqlExecutor.execSQL(sql);
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

    @Override
    public void run(String sql)
    {
        //ignore
    }


}
