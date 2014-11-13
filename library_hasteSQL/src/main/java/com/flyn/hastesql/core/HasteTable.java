package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.util.ReflectUtils;
import com.flyn.hastesql.util.SQLUtils;

/**
 * Created by flyn on 2014-11-11.
 */
public class HasteTable
{

    private final String tableName;
    private Property[] allColumns;
    private Property[] pkColumns;
    private SQLiteStatement insertSQLiteStatement;


    protected HasteTable(String tableName, Class<? extends HasteModel> clz)
    {
        this.tableName = tableName;
        init(clz);
    }

    private void init(Class<? extends HasteModel> clz)
    {
        allColumns = ReflectUtils.getPropertyArray(clz);
    }

    protected void compileStatements(SQLiteDatabase db)
    {
        compileInsertSQLiteStatement(db);
    }

    public String getTableName()
    {
        return tableName;
    }

    public Property[] getAllColumns()
    {
        return allColumns;
    }

    public Property[] getPkColumns()
    {
        return pkColumns;
    }

    private void compileInsertSQLiteStatement(SQLiteDatabase db)
    {
        String insertSQL = SQLUtils.createSQLInsert(getTableName(), getAllColumns());

        insertSQLiteStatement = db.compileStatement(insertSQL);
    }


    public SQLiteStatement getInsertSQLiteStatement()
    {
        return insertSQLiteStatement;
    }
}
