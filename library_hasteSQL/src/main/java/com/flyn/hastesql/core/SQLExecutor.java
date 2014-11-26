package com.flyn.hastesql.core;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;

import com.flyn.hastesql.util.CursorUtils;
import com.flyn.hastesql.util.LogUtils;

import java.lang.reflect.Constructor;
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

    public long insert(String sql, Object[] objects)
    {
        debugSql(sql);
        long rowId = -1;
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            SQLiteStatement sqLiteStatement = null;
            try
            {
                sqLiteStatement = getSQLiteStatement(sql, objects);
                rowId = sqLiteStatement.executeInsert();
            } finally
            {
                CursorUtils.closeQuietly(sqLiteStatement);
            }
            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.unlock();
        }
        return rowId;
    }

    public long[] insertList(String sql, List<Object[]> objects)
    {
        debugSql(sql);
        long[] rowId = new long[objects.size()];
        try
        {
            mWriteLock.lock();
            db.beginTransaction();
            SQLiteStatement sqLiteStatement = null;

            long time=0;
            for (int i = 0, size = objects.size(); i < size; i++)
            {
                try
                {
                    sqLiteStatement = db.compileStatement(sql);

                    long time1=System.currentTimeMillis();
                    Object [] array= objects.get(i);
                    for (int j = 0; j <array.length; j++)
                    {
                        DatabaseUtils.bindObjectToProgram(sqLiteStatement, j + 1, array[j]);
                    }
                    time+=(System.currentTimeMillis()-time1);

                    rowId[i] = sqLiteStatement.executeInsert();


                } finally
                {
                    CursorUtils.closeQuietly(sqLiteStatement);
                }
            }

            LogUtils.d("绑定数据耗时:"+time);

            db.setTransactionSuccessful();
        } finally
        {
            db.endTransaction();
            mWriteLock.unlock();
        }
        return rowId;
    }

    private SQLiteStatement getSQLiteStatement(String sql, Object[] objects)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
        {
            try
            {
                Constructor<SQLiteStatement> constructor = SQLiteStatement.class.getDeclaredConstructor(SQLiteDatabase.class, String.class,
                        Object[].class);
                constructor.setAccessible(true);
                SQLiteStatement sqLiteStatement = constructor.newInstance(db, sql, objects);
                return sqLiteStatement;
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } else
        {
            try
            {
                Constructor<SQLiteStatement> constructor = SQLiteStatement.class.getDeclaredConstructor(SQLiteDatabase.class, String.class);
                constructor.setAccessible(true);
                SQLiteStatement sqLiteStatement = constructor.newInstance(db, sql);
                for (int i = 0; i < objects.length; i++)
                {
                    DatabaseUtils.bindObjectToProgram(sqLiteStatement, i + 1, objects[i]);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void debugSql(String sql)
    {
        LogUtils.d(sql);
    }
}
