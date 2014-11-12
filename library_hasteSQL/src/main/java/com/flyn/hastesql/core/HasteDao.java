package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

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

    protected HasteDao(SQLiteDatabase db, SQLExecutor sqlExecutor, String tableName, Class<? extends HasteModel> hasteModelClz)
    {
        this.sqlExecutor = sqlExecutor;
        this.hasteTable = new HasteTable(db, tableName, hasteModelClz);
        createTableIfNotExits();
    }

    protected void createTableIfNotExits()
    {

        String checkTableSQL = SQLUtils.createSQLCheckTableExits(hasteTable.getTableName());
        if (sqlExecutor.isEmpty(checkTableSQL))
        {
            //如果为空则建表
            String createTableSQL = SQLUtils.createSQLCreateTable(hasteTable.getTableName(), hasteTable.getAllColumns());
            sqlExecutor.execSQL(createTableSQL);
        }
    }

    @Override
    public void insert(HasteModel hasteModel)
    {
        SQLiteStatement sqLiteStatement = hasteTable.getInsertSQLiteStatement();
        synchronized (sqLiteStatement)
        {
            SQLUtils.statementBindValue(sqLiteStatement, SQLUtils.propertyBindValue(hasteTable.getAllColumns(), hasteModel));
        }

        sqlExecutor.execInsert(sqLiteStatement);
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
