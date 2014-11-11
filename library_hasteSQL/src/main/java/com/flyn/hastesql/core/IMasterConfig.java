package com.flyn.hastesql.core;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 */
public interface IMasterConfig
{
    public void onCreate(SQLiteDatabase db);

    public void onDrop(SQLiteDatabase db);

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    public int dbVersion();

    public String dbName();

    public String dbDir();

    public <T extends IHasteDaoImpl> List<T> getDaoImplList();

}
