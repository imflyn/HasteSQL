package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by flyn on 2014-11-11.
 */
public interface IHasteConfig
{
    public abstract void onCreate(SQLiteDatabase db);

    public abstract void onDrop(SQLiteDatabase db);

    public abstract void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    public abstract int dbVersion();

    public abstract String dbName();


}
