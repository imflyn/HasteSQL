package com.flyn.hastesql.core;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.util.CursorUtils;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.hastesql.util.SQLUtils;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by flyn on 2014-11-11.
 * <p/>
 * Thread-Safe
 */
public class SQLExecutor
{

    private final ReentrantReadWriteLock.ReadLock mReadLock;
    private final ReentrantReadWriteLock.WriteLock mWriteLock;

    private final SQLiteDatabase db;

    public SQLExecutor(SQLiteDatabase db)
    {
        this.db = db;
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        this.mReadLock = lock.readLock();
        this.mWriteLock = lock.writeLock();
    }


    public void insert(HasteModel hasteModel)
    {

    }

    public void update(HasteModel hasteModel)
    {

    }

    public void insertOrReplace(HasteModel hasteModel)
    {

    }

    public void delete(HasteModel hasteModel)
    {

    }

    public <T extends HasteModel> T queryFirst(Class<T> clz)
    {
        return null;
    }

    public <T extends HasteModel> List<T> queryAll(Class<T> clz)
    {
        return null;
    }

    public boolean isEmpty(String sql)
    {
        Cursor cursor = execQuery(sql);
        if (cursor != null)
        {
            try
            {
                if (cursor.moveToNext())
                {
                    int count = cursor.getInt(0);
                    if (count > 0)
                    {
                        return false;
                    }
                }
            } finally
            {
                CursorUtils.closeQuietly(cursor);
            }
        }

        return true;
    }


    public Cursor execQuery(String sql)
    {
        return execQuery(sql, null);
    }

    public Cursor execQuery(String sql, String[] selectionArgs)
    {
        debugSql(sql);
        Cursor cursor;
        try
        {
            mReadLock.lock();
            cursor = db.rawQuery(sql, selectionArgs);
        } finally
        {
            mReadLock.unlock();
        }
        return cursor;
    }


    public void execSQL(String sql)
    {
        debugSql(sql);
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            db.execSQL(sql);
            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.unlock();
        }
    }

    public void execSQL(String sql, String[] selectionArgs)
    {
        debugSql(sql);
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            db.execSQL(sql, selectionArgs);
            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.unlock();
        }
    }


    public void execSQLWithStmt(String sql)
    {
        debugSql(sql);

        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            SQLiteStatement sqLiteStatement = db.compileStatement(sql);
            sqLiteStatement.execute();
            db.setTransactionSuccessful();
            sqLiteStatement.close();
        } finally
        {
            db.endTransaction();
            mWriteLock.lock();
        }
    }

    public void execInsert(SQLiteStatement sqLiteStatement, Property[] properties)
    {
        debugSql(sqLiteStatement.toString());
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            SQLUtils.statementBindValue(sqLiteStatement, properties);
            sqLiteStatement.executeInsert();
            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.lock();
        }
    }

    public void execInsertAll(SQLiteStatement sqLiteStatement, Property[] properties, List<? extends HasteModel> hasteModelList)
    {
        debugSql(sqLiteStatement.toString());
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            for (HasteModel hasteModel : hasteModelList)
            {
                Property[] propertyArray = SQLUtils.propertyBindValue(properties, hasteModel);
                SQLUtils.statementBindValue(sqLiteStatement, propertyArray);
                sqLiteStatement.executeInsert();
            }
            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.lock();
        }
    }


    private void debugSql(String sql)
    {
        LogUtils.d(sql);
    }
}
