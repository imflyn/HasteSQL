package com.flyn.hastesql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.core.IMasterConfig;

import java.util.HashMap;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Thread-Safe
 */
public class HasteSQL
{
    private static HasteSQL hasteSQL;
    private final Context mContext;
    private final HashMap<String, HasteMaster> hasteMasterMap = new HashMap<String, HasteMaster>();
    private Object mLock = new Object();

    private HasteSQL(Context context)
    {
        mContext = context.getApplicationContext();
    }

    private static synchronized HasteSQL getInstance(Context context)
    {
        if (null == hasteSQL)
        {
            hasteSQL = new HasteSQL(context);

        }
        return hasteSQL;
    }

    public static HasteMaster createDefault(Context context)
    {
        return createNew(context, DEFAULT_CONFIG);
    }

    public static HasteMaster createNew(Context context, IMasterConfig masterConfig)
    {

        HasteSQL hasteSQL = getInstance(context);

        HasteMaster hasteMaster;
        synchronized (hasteSQL.mLock)
        {
            hasteMaster = hasteSQL.hasteMasterMap.get(masterConfig.dbName());
            if (hasteMaster == null)
            {
                hasteMaster = HasteMaster.newInstance(context, masterConfig);
                hasteSQL.hasteMasterMap.put(masterConfig.dbName(), hasteMaster);
            }
        }
        return hasteMaster;
    }


    private static IMasterConfig DEFAULT_CONFIG = new IMasterConfig()
    {
        @Override
        public void onCreate(SQLiteDatabase db)
        {

        }

        @Override
        public void onDrop(SQLiteDatabase db)
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
}
