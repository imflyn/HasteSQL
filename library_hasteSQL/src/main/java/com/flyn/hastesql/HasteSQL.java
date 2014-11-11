package com.flyn.hastesql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.core.IMasterConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Thread-Safe
 */
public class HasteSQL
{
    private static HasteSQL hasteSQL;
    private static IMasterConfig DEFAULT_MASTER_CONFIG = new IMasterConfig()
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
    private final Context mContext;
    private final ConcurrentHashMap<String, HasteMaster> hasteMasterMap = new ConcurrentHashMap<String, HasteMaster>();

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
        return createNew(context, DEFAULT_MASTER_CONFIG);
    }

    public static HasteMaster createNew(Context context, IMasterConfig masterConfig)
    {
        HasteSQL hasteSQL = getInstance(context);

        HasteMaster hasteMaster = hasteSQL.hasteMasterMap.get(masterConfig.dbName());
        if (hasteMaster == null)
        {
            synchronized (hasteSQL.hasteMasterMap)
            {
                hasteMaster = HasteMaster.newInstance(context, masterConfig);
                hasteSQL.hasteMasterMap.put(masterConfig.dbName(), hasteMaster);
            }
        }
        return hasteMaster;
    }

}
