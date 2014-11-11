package com.flyn.hastesql;

import android.content.Context;

import com.flyn.hastesql.core.HasteMaster;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Thread-Safe
 */
public class HasteSQL
{
    private final Context mContext;
    private final ConcurrentHashMap<String, HasteMaster> hasteMasterMap = new ConcurrentHashMap<String, HasteMaster>();
    private static HasteSQL hasteSQL;

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


    public static HasteSQL createDefault(Context context)
    {
        return createNew(context, null);
    }

    public static HasteSQL createNew(Context context, String dbName)
    {
        HasteSQL hasteSQL = getInstance(context);


        return hasteSQL;
    }

}
