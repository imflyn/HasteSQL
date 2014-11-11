package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;

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


}
