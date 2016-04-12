package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by flyn on 2014-11-11.
 */
public interface IHasteConfig
{
    void onCreate(SQLiteDatabase db);

    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    int dbVersion();

    String dbName();
}
