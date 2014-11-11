package com.flyn.hastesql.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by flyn on 2014-11-11.
 * <p/>
 * Thread-Safe
 */
public class HasteMaster implements HasteOperation
{

    private final IMasterConfig masterConfig;
    private final HasteSQLiteOpenHelper hasteSQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Map<String, HasteDao> hasteDaoMap = new HashMap<String, HasteDao>();

    private HasteMaster(Context context, IMasterConfig masterConfig)
    {
        this.masterConfig = masterConfig;
        this.hasteSQLiteOpenHelper = new HasteSQLiteOpenHelper(context, masterConfig);
        init();

    }

    public static HasteMaster newInstance(Context context, IMasterConfig masterConfig)
    {
        return new HasteMaster(context, masterConfig);
    }

    private void init()
    {
        this.hasteDaoMap.clear();
        this.sqLiteDatabase = this.hasteSQLiteOpenHelper.getWritableDatabase();
    }

    private synchronized HasteDao getHasteDao(Class<? extends HasteModel> clz, String tableName)
    {
        HasteDao hasteDao = hasteDaoMap.get(tableName);
        if (null == hasteDao)
        {
            hasteDao = new HasteDao(this.sqLiteDatabase, tableName, clz);

            this.hasteDaoMap.put(tableName, hasteDao);
        }
        return hasteDao;
    }

    @Override
    public void insert(HasteModel hasteModel)
    {
        getHasteDao(hasteModel.getClass(), hasteModel.getClass().getSimpleName()).insert(hasteModel);

    }

    public void insert(HasteModel hasteModel, String prefix, String suffix)
    {
        getHasteDao(hasteModel.getClass(), prefix + hasteModel.getClass().getSimpleName() + suffix).insert(hasteModel);
    }

    @Override
    public void update(HasteModel hasteModel)
    {
        getHasteDao(hasteModel.getClass(), hasteModel.getClass().getSimpleName()).update(hasteModel);
    }

    public void update(HasteModel hasteModel, String prefix, String suffix)
    {
        getHasteDao(hasteModel.getClass(), prefix + hasteModel.getClass().getSimpleName() + suffix).update(hasteModel);
    }


    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {
        getHasteDao(hasteModel.getClass(), hasteModel.getClass().getSimpleName()).update(hasteModel);
    }

    public void insertOrReplace(HasteModel hasteModel, String prefix, String suffix)
    {
        getHasteDao(hasteModel.getClass(), prefix + hasteModel.getClass().getSimpleName() + suffix).insertOrReplace(hasteModel);
    }

    @Override
    public void delete(HasteModel hasteModel)
    {
        getHasteDao(hasteModel.getClass(), hasteModel.getClass().getSimpleName()).delete(hasteModel);
    }

    public void delete(HasteModel hasteModel, String prefix, String suffix)
    {
        getHasteDao(hasteModel.getClass(), prefix + hasteModel.getClass().getSimpleName() + suffix).delete(hasteModel);
    }

    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryFirst(clz);
    }

    public <T extends HasteModel> T queryFirst(Class<T> clz, String prefix, String suffix)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryFirst(clz);
    }

    @Override
    public <T extends HasteModel> List<T> queryAll(Class<T> clz)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryAll(clz);
    }

    public <T extends HasteModel> List<T> queryAll(Class<T> clz, String prefix, String suffix)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryAll(clz);
    }


    private static class HasteSQLiteOpenHelper extends SQLiteOpenHelper
    {
        private final IMasterConfig masterConfig;

        private HasteSQLiteOpenHelper(Context context, IMasterConfig masterConfig)
        {
            super(context, masterConfig.dbName(), null, masterConfig.dbVersion());
            this.masterConfig = masterConfig;
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
}
