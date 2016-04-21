package com.flyn.hastesql.core;

import android.database.Cursor;

import com.flyn.hastesql.optional.ConditionBuilder;
import com.flyn.hastesql.optional.ConditionExpression;
import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.util.CursorUtils;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.hastesql.util.ReflectUtils;
import com.flyn.hastesql.util.SQLUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyn on 2014-11-10.
 * Thread-Safe
 */
public class HasteDao
{
    private final HasteTable                  hasteTable;
    private final SQLExecutor                 sqlExecutor;
    private       Class<? extends HasteModel> hasteModelClz;

    protected HasteDao(SQLExecutor sqlExecutor, String tableName, Class<? extends HasteModel> hasteModelClz)
    {
        this.sqlExecutor = sqlExecutor;
        this.hasteModelClz = hasteModelClz;
        this.hasteTable = new HasteTable(tableName, hasteModelClz);
        createTableIfNotExits(tableName, this.hasteTable.getAllColumns());
    }

    protected void createTableIfNotExits(String tableName, Property[] properties)
    {
        String checkTableSQL = SQLUtils.createSQLCheckTableExits(tableName);
        Cursor cursor = sqlExecutor.execQuery(checkTableSQL);
        if (!CursorUtils.checkTableExist(cursor) && ReflectUtils.checkPropertiesValidity(properties))
        {
            //如果不存在则建表
            String createTableSQL = SQLUtils.createSQLCreateTable(tableName, properties);
            sqlExecutor.execSQL(createTableSQL);
        }
    }

    public void insert(HasteModel hasteModel)
    {
        String sql = SQLUtils.createSQLInsert(hasteTable.getTableName(), hasteTable.getAllColumns());
        Object[] objects;
        try
        {
            objects = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, true);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return;
        }
        long rowId = sqlExecutor.insert(sql, objects);
        if (hasteTable.isAutoIncrease() && rowId > 0)
        {
            try
            {
                hasteTable.getPrimaryKey().getConverter().setValue(rowId, hasteModel);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void insertAll(List<? extends HasteModel> hasteModelList)
    {
        String sql = SQLUtils.createSQLInsert(hasteTable.getTableName(), hasteTable.getAllColumns());
        List<Object[]> objects = new ArrayList<>(hasteModelList.size());
        try
        {
            for (int i = 0, size = hasteModelList.size(); i < size; i++)
            {
                objects.add(ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModelList.get(i), true));
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return;
        }
        long[] rowIdArray = sqlExecutor.insertList(sql, objects);
        if (hasteTable.isAutoIncrease())
        {
            for (int i = 0, size = hasteModelList.size(); i < size; i++)
            {
                if (rowIdArray[i] > 0)
                {
                    try
                    {
                        hasteTable.getPrimaryKey().getConverter().setValue(rowIdArray[i], hasteModelList.get(i));
                    } catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void update(ConditionBuilder conditionBuilder)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" UPDATE ").append(hasteTable.getTableName()).append(" ");
        stringBuilder.append(conditionBuilder.build());
        sqlExecutor.execSQL(stringBuilder.toString());
    }

    public void update(HasteModel hasteModel)
    {
        if (hasteTable.hasPrimaryKey())
        {
            String sql = SQLUtils.createSQLUpdateByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey(), hasteTable.getAllColumns());
            Object[] srcArray;
            try
            {
                srcArray = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, false);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return;
            }
            Object[] dstArray = new Object[srcArray.length + 1];
            System.arraycopy(srcArray, 0, dstArray, 0, srcArray.length);
            try
            {
                dstArray[dstArray.length - 1] = ReflectUtils.getFieldValue(hasteTable.getPrimaryKey(), hasteModel);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return;
            }

            sqlExecutor.execSQL(sql, dstArray);
        } else
        {
            //not support
            throw new IllegalArgumentException("Can not delete entity with no primary key.");
        }
    }

    public void updateAll(List<? extends HasteModel> hasteModelList)
    {
        List<Object[]> objects = new ArrayList<>(hasteModelList.size());
        String sql = SQLUtils.createSQLUpdateByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey(), hasteTable.getAllColumns());
        HasteModel hasteModel;
        for (int i = 0, size = hasteModelList.size(); i < size; i++)
        {
            hasteModel = hasteModelList.get(i);
            update(hasteModel);
        }
        sqlExecutor.execSQLList(sql, objects);
    }

    public void insertOrReplace(HasteModel hasteModel)
    {
        boolean skipPrimaryKey = ReflectUtils.getRowIdValue(hasteTable, hasteModel) <= 0;
        String sql = SQLUtils.createSQLInsertOrReplace(hasteTable.getTableName(), hasteTable.getAllColumns(), skipPrimaryKey);
        Object[] objects;
        try
        {
            objects = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, skipPrimaryKey);
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return;
        }
        long rowId = sqlExecutor.insert(sql, objects);
        if (rowId > 0 && hasteTable.hasPrimaryKey())
        {
            try
            {
                hasteTable.getPrimaryKey().getConverter().setValue(rowId, hasteModel);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList)
    {
        HasteModel hasteModel;
        for (int i = 0, size = hasteModelList.size(); i < size; i++)
        {
            hasteModel = hasteModelList.get(i);
            insertOrReplace(hasteModel);
        }
    }

    public void delete(HasteModel hasteModel)
    {
        if (hasteTable.hasPrimaryKey())
        {
            String sql = SQLUtils.createSQLDeleteByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey());
            Object[] keyValues;
            try
            {
                keyValues = new Object[]{hasteTable.getPrimaryKey().getConverter().getValue(hasteModel)};
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return;
            }
            sqlExecutor.execSQL(sql, keyValues);
        } else
        {
            String sql = SQLUtils.createSQLDelete(hasteTable.getTableName(), hasteTable.getAllColumns());
            Object[] objects;
            try
            {
                objects = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModel, true);
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return;
            }
            sqlExecutor.execSQL(sql, objects);
        }
    }

    public void delete(ConditionExpression conditionExpression)
    {
        StringBuilder stringBuilder = new StringBuilder();
        String sql = SQLUtils.createSQLDelete(hasteTable.getTableName());
        stringBuilder.append(sql).append(" WHERE ").append(conditionExpression.toString());
        sqlExecutor.execSQL(stringBuilder.toString());
    }

    public void deleteAll()
    {
        String sql = SQLUtils.createSQLDelete(hasteTable.getTableName());
        sqlExecutor.execSQL(sql);
    }

    public void deleteAll(List<? extends HasteModel> hasteModelList)
    {
        List<Object[]> objects = new ArrayList<>(hasteModelList.size());
        if (hasteTable.hasPrimaryKey())
        {
            String sql = SQLUtils.createSQLDeleteByKey(hasteTable.getTableName(), hasteTable.getPrimaryKey());

            try
            {
                for (int i = 0, size = hasteModelList.size(); i < size; i++)
                {
                    Object[] keyValues = new Object[]{hasteTable.getPrimaryKey().getConverter().getValue(hasteModelList.get(i))};
                    objects.add(keyValues);
                }
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return;
            }
            sqlExecutor.execSQLList(sql, objects);
        } else
        {
            String sql = SQLUtils.createSQLDelete(hasteTable.getTableName(), hasteTable.getAllColumns());
            try
            {
                for (int i = 0, size = hasteModelList.size(); i < size; i++)
                {
                    Object[] keyValues = ReflectUtils.getFieldValueArray(hasteTable.getAllColumns(), hasteModelList.get(i), true);
                    objects.add(keyValues);
                }
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
                return;
            }
            sqlExecutor.execSQLList(sql, objects);
        }
    }


    public <T extends HasteModel> List<T> queryAll()
    {
        String sql = SQLUtils.createSQLSelect(hasteTable.getTableName());
        Cursor cursor = sqlExecutor.execQuery(sql);
        List<T> entities = null;
        try
        {
            entities = (List<T>) CursorUtils.cursorToEntities(hasteModelClz, cursor, hasteTable.getAllColumns());
        } catch (Exception e)
        {
            throw new RuntimeException(e);
            //            e.printStackTrace();
        } finally
        {
            cursor.close();
        }
        return entities;
    }


    public <T extends HasteModel> T queryFirst()
    {
        String sql = SQLUtils.createSQLSelect(hasteTable.getTableName());
        Cursor cursor = sqlExecutor.execQuery(sql);
        List<T> entities = null;
        try
        {
            entities = (List<T>) CursorUtils.cursorToEntities(hasteModelClz, cursor, hasteTable.getAllColumns(), false);
        } catch (Exception e)
        {
            LogUtils.e(e);
        } finally
        {
            cursor.close();
        }
        return entities.isEmpty() ? null : entities.get(0);
    }


    public <T extends HasteModel> T queryFirst(ConditionBuilder conditionBuilder)
    {
        String sql = SQLUtils.createSQLSelect(hasteTable.getTableName());
        if (null != conditionBuilder)
        {
            sql = sql + conditionBuilder.build();
        }
        Cursor cursor = sqlExecutor.execQuery(sql);
        List<T> entities = null;
        try
        {
            entities = (List<T>) CursorUtils.cursorToEntities(hasteModelClz, cursor, hasteTable.getAllColumns(), false);
        } catch (Exception e)
        {
            LogUtils.e(e);
        } finally
        {
            cursor.close();
        }
        return entities.isEmpty() ? null : entities.get(0);
    }

    public <T extends HasteModel> T queryByKey(Object key)
    {
        if (!hasteTable.hasPrimaryKey())
        {
            //not support
            throw new IllegalArgumentException("Can not query entity by key with no primary key.");
        }

        StringBuilder sqlBuilder = new StringBuilder(SQLUtils.createSQLSelect(hasteTable.getTableName()));
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.equals(hasteTable.getPrimaryKey().getName(), key);
        conditionBuilder.where(conditionExpression);
        sqlBuilder.append(conditionBuilder.build());
        Cursor cursor = sqlExecutor.execQuery(sqlBuilder.toString());
        List<T> entities = null;
        try
        {
            entities = (List<T>) CursorUtils.cursorToEntities(hasteModelClz, cursor, hasteTable.getAllColumns(), false);
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            cursor.close();
        }
        return entities.isEmpty() ? null : entities.get(0);
    }

    public <T extends HasteModel> List<T> query(ConditionBuilder conditionBuilder)
    {
        String sql = SQLUtils.createSQLSelect(hasteTable.getTableName());
        if (null != conditionBuilder)
        {
            sql = sql + conditionBuilder.build();
        }
        Cursor cursor = sqlExecutor.execQuery(sql);
        List<T> entities = null;
        try
        {
            entities = (List<T>) CursorUtils.cursorToEntities(hasteModelClz, cursor, hasteTable.getAllColumns());
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            cursor.close();
        }
        return entities;
    }

    public void run(String sql)
    {
        sqlExecutor.execSQL(sql);
    }

    public void run(String sql, Object[] args)
    {
        sqlExecutor.execSQL(sql, args);
    }

    public Cursor query(String sql, String[] args)
    {
        return sqlExecutor.execQuery(sql, args);
    }

}
