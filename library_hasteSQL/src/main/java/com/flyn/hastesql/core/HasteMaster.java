package com.flyn.hastesql.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flyn.hastesql.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by flyn on 2014-11-11.
 * <p/>
 * Thread-Safe
 */
public class HasteMaster extends SQLiteOpenHelper
{
    private final IMasterConfig masterConfig;
    private ConcurrentHashMap<Class<? extends HasteModel>, IHasteDaoImpl> hasteDaoImplMap = new ConcurrentHashMap<Class<? extends HasteModel>,
            IHasteDaoImpl>();

    public HasteMaster(Context context, IMasterConfig masterConfig)
    {
        super(context, masterConfig.dbName(), null, masterConfig.dbVersion());
        this.masterConfig = masterConfig;
        init();
    }

    private void init()
    {
        hasteDaoImplMap.clear();

        List<IHasteDaoImpl> iHasteDaoImplList = this.masterConfig.getDaoImplList();
        if (!CollectionUtils.isEmpty(iHasteDaoImplList))
        {
            for (IHasteDaoImpl iHasteDaoImpl : iHasteDaoImplList)
            {
                hasteDaoImplMap.put(iHasteDaoImpl.getHasteModelClz(), iHasteDaoImpl);
            }
        }
    }

    public static HasteMaster newInstence(Context context, IMasterConfig masterConfig)
    {
        return new HasteMaster(context, masterConfig);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        this.masterConfig.onCreate(db);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        this.masterConfig.onUpgrade(db, oldVersion, newVersion);
    }
}
