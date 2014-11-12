package com.flyn.hastesql.core;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

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

    private final ReentrantReadWriteLock mLock;
    private final ReentrantReadWriteLock.ReadLock mReadLock;
    private final ReentrantReadWriteLock.WriteLock mWriteLock;

    private final SQLiteDatabase db;

    public SQLExecutor(SQLiteDatabase db)
    {
        this.db = db;
        this.mLock = new ReentrantReadWriteLock();
        this.mReadLock = this.mLock.readLock();
        this.mWriteLock = this.mLock.writeLock();
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
                        return true;
                    }
                }
            } finally
            {
                CursorUtils.closeQuietly(cursor);
            }
        }

        return false;
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

    public void execInsert(SQLiteStatement sqLiteStatement, HasteModel hasteModel)
    {
        debugSql(sqLiteStatement.toString());

        synchronized (sqLiteStatement)
        {
            //TODO 创建Properties
            SQLUtils.statementBindValue(sqLiteStatement, null);

        }
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            sqLiteStatement.executeInsert();
            db.setTransactionSuccessful();
            sqLiteStatement.close();
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
