package com.flyn.hastesql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.core.IHasteConfig;

import java.util.HashMap;

/**
 * Created by flyn on 2014-11-10.
 * Thread-Safe
 */
public class HasteSQL
{
    private static final IHasteConfig DEFAULT_CONFIG = new IHasteConfig()
    {
        @Override
        public void onCreate(SQLiteDatabase db)
        {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }

        @Override
        public int dbVersion()
        {
            return 1;
        }

        @Override
        public String dbName()
        {
            return "database.db";
        }

    };
    private static HasteSQL hasteSQL;
    private final HashMap<String, HasteMaster> hasteMasterMap = new HashMap<String, HasteMaster>();
    private final Object mLock = new Object();

    private HasteSQL()
    {

    }

    private static synchronized HasteSQL getInstance(Context context)
    {
        if (null == hasteSQL)
        {
            hasteSQL = new HasteSQL();
        }
        return hasteSQL;
    }

    public static HasteMaster createDefault(Context context)
    {
        return createNew(context, DEFAULT_CONFIG);
    }

    public static HasteMaster createNew(Context context, IHasteConfig hasteConfig)
    {

        HasteSQL hasteSQL = getInstance(context);

        HasteMaster hasteMaster;
        synchronized (hasteSQL.mLock)
        {
            hasteMaster = hasteSQL.hasteMasterMap.get(hasteConfig.dbName());
            if (hasteMaster == null)
            {
                hasteMaster = HasteMaster.newInstance(context, hasteConfig);
                hasteSQL.hasteMasterMap.put(hasteConfig.dbName(), hasteMaster);
            }
        }
        return hasteMaster;
    }
}
