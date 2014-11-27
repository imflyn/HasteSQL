package com.flyn.hastesql.core;

import com.flyn.hastesql.optional.ConditionExpression;
import com.flyn.hastesql.util.ReflectUtils;
import com.flyn.hastesql.util.SQLUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Thread-Safe
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
        long rowId = sqlExecutor.insert(sql, objects);
        if (hasteTable.isAutoIncrease() && rowId != -1)
        {
            hasteModel.setRowId(rowId);
        }
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
        long[] rowId = sqlExecutor.insertList(sql, objects);
        if (hasteTable.isAutoIncrease())
        {
            for (int i = 0, size = hasteModelList.size(); i < size; i++)
            {
                if (rowId[i] != -1)
                {
                    hasteModelList.get(i).setRowId(rowId[i]);
                }
            }
        }
    }


    @Override
    public void update(HasteModel hasteModel)
    {
        if (hasteTable.hasPrimaryKey())
        {
            String sql = SQLUtils.createSQLUpdateByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey(), hasteTable.getAllColumns());
            Object[] srcArray = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, false);
            Object[] dstArray = new Object[srcArray.length + 1];

            System.arraycopy(srcArray, 0, dstArray, 0, srcArray.length);
            dstArray[dstArray.length - 1] = hasteModel.getRowId();
            sqlExecutor.execSQL(sql, dstArray);

        } else
        {
            //not support
            throw new IllegalArgumentException("Can not delete entity with no primary key.");
        }
    }

    @Override
    public void updateAll(List<? extends HasteModel> hasteModelList)
    {
        List<Object[]> objects = new ArrayList<Object[]>(hasteModelList.size());
        if (hasteTable.hasPrimaryKey())
        {
            String sql = SQLUtils.createSQLUpdateByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey(), hasteTable.getAllColumns());
            HasteModel hasteModel;
            for (int i = 0, size = hasteModelList.size(); i < size; i++)
            {
                hasteModel = hasteModelList.get(i);
                Object[] srcArray = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, false);
                Object[] dstArray = new Object[srcArray.length + 1];

                System.arraycopy(srcArray, 0, dstArray, 0, srcArray.length);
                dstArray[dstArray.length - 1] = hasteModel.getRowId();
                objects.add(dstArray);
            }
            sqlExecutor.execSQLList(sql, objects);
        } else
        {
            //not support
            throw new IllegalArgumentException("Can not delete entity with no primary key.");
        }
    }

    public void update(Class<? extends HasteModel> clz, ConditionExpression conditionExpression)
    {
        StringBuilder stringBuilder = new StringBuilder();
        String sql = SQLUtils.createSQLUpdate(hasteTable.getTableName(), hasteTable.getAllColumns());
        stringBuilder.append(sql).append(" WHERE ").append(conditionExpression.toString());
        sqlExecutor.execSQL(stringBuilder.toString());
    }

    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {
        String sql = SQLUtils.createSQLInsertOrReplace(hasteTable.getTableName(), hasteTable.getAllColumns());
        Object[] objects = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, false);
        sqlExecutor.execSQL(sql, objects);
    }

    @Override
    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList)
    {
        String sql = SQLUtils.createSQLInsertOrReplace(hasteTable.getTableName(), hasteTable.getAllColumns());
        List<Object[]> objects = new ArrayList<Object[]>(hasteModelList.size());
        for (int i = 0, size = hasteModelList.size(); i < size; i++)
        {
            objects.add(ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModelList.get(i), false));
        }
        sqlExecutor.execSQLList(sql, objects);
    }

    @Override
    public void delete(HasteModel hasteModel)
    {
        if (hasteTable.hasPrimaryKey())
        {
            String sql = SQLUtils.createSQLDeleteByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey());
            Object[] keyValues = new Object[]{ReflectUtils.getFieldValue(hasteTable.getPrimaryKey().getField(), hasteModel)};
            sqlExecutor.execSQL(sql, keyValues);
        } else
        {
            String sql = SQLUtils.createSQLDelete(hasteTable.getTableName(), hasteTable.getAllColumns());
            Object[] objects = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, true);
            sqlExecutor.execSQL(sql, objects);
        }
    }

    @Override
    public void delete(Class<? extends HasteModel> clz, ConditionExpression conditionExpression)
    {
        StringBuilder stringBuilder = new StringBuilder();
        String sql = SQLUtils.createSQLDelete(hasteTable.getTableName());
        stringBuilder.append(sql).append(" WHERE ").append(conditionExpression.toString());
        sqlExecutor.execSQL(stringBuilder.toString());
    }

    @Override
    public void deleteAll(Class<? extends HasteModel> clz)
    {
        String sql = SQLUtils.createSQLDelete(hasteTable.getTableName());
        sqlExecutor.execSQL(sql);
    }

    @Override
    public void deleteAll(List<? extends HasteModel> hasteModelList)
    {
        List<Object[]> objects = new ArrayList<Object[]>(hasteModelList.size());
        if (hasteTable.hasPrimaryKey())
        {
            String sql = SQLUtils.createSQLDeleteByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey());

            for (int i = 0, size = hasteModelList.size(); i < size; i++)
            {
                Object[] keyValues = new Object[]{ReflectUtils.getFieldValue(hasteTable.getPrimaryKey().getField(), hasteModelList.get(i))};
                objects.add(keyValues);
            }
            sqlExecutor.execSQLList(sql, objects);
        } else
        {
            String sql = SQLUtils.createSQLDelete(hasteTable.getTableName(), hasteTable.getAllColumns());
            for (int i = 0, size = hasteModelList.size(); i < size; i++)
            {
                Object[] keyValues = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModelList.get(i), true);
                objects.add(keyValues);
            }
            sqlExecutor.execSQLList(sql, objects);
        }
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
        sqlExecutor.execSQL(sql);
    }

    @Override
    public void run(String sql, Object[] args)
    {
        sqlExecutor.execSQL(sql, args);
    }

}
