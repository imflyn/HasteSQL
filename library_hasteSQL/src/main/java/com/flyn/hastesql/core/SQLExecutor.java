package com.flyn.hastesql.core;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flyn.hastesql.util.CursorUtils;
import com.flyn.hastesql.util.LogUtils;

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
        LogUtils.d(sql);
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

    public void execSQL(String sql, Object[] values)
    {
        debugSql(sql);
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            db.execSQL(sql, values);
            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.unlock();
        }
    }

    public void execSQLList(String sql, List<Object[]> values)
    {
        debugSql(sql);
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            for (int i = 0, size = values.size(); i < size; i++)
            {
                db.execSQL(sql, values.get(i));
            }
            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.unlock();
        }
    }


    private void debugSql(String sql)
    {
        LogUtils.d(sql);
    }
}
