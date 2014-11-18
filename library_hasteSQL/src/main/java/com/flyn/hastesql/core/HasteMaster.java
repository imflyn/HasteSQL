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
    private final Map<String, HasteDao> hasteDaoMap = new HashMap<String, HasteDao>();
    private final HasteSQLiteOpenHelper hasteSQLiteOpenHelper;
    private SQLiteDatabase db;
    private SQLExecutor sqlExecutor;

    private HasteMaster(Context context, IHasteConfig hasteConfig)
    {
        this.hasteSQLiteOpenHelper = new HasteSQLiteOpenHelper(context, hasteConfig);
        init();

    }

    public static HasteMaster newInstance(Context context, IHasteConfig hasteConfig)
    {
        return new HasteMaster(context, hasteConfig);
    }

    private void init()
    {
        this.hasteDaoMap.clear();
        this.db = this.hasteSQLiteOpenHelper.getWritableDatabase();
        this.sqlExecutor = new SQLExecutor(this.db);
    }

    private synchronized HasteDao getHasteDao(Class<? extends HasteModel> clz, String tableName)
    {
        HasteDao hasteDao = hasteDaoMap.get(tableName);
        if (null == hasteDao)
        {
            hasteDao = new HasteDao(this.sqlExecutor, tableName, clz);
            this.hasteDaoMap.put(tableName, hasteDao);
        }
        return hasteDao;
    }

    public SQLExecutor getSqlExecutor()
    {
        return sqlExecutor;
    }

    @Override
    public void insert(HasteModel hasteModel)
    {
        getHasteDao(hasteModel.getClass(), hasteModel.getClass().getSimpleName()).insert(hasteModel);

    }

    @Override
    public void insertAll(List<? extends HasteModel> hasteModelList)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, clz.getSimpleName()).insertAll(hasteModelList);
    }

    public void insertAll(List<HasteModel> hasteModelList, String prefix, String suffix)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).insertAll(hasteModelList);
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
        getHasteDao(hasteModel.getClass(), hasteModel.getClass().getSimpleName()).insertOrReplace(hasteModel);
    }

    public void insertOrReplace(HasteModel hasteModel, String prefix, String suffix)
    {
        getHasteDao(hasteModel.getClass(), prefix + hasteModel.getClass().getSimpleName() + suffix).insertOrReplace(hasteModel);
    }

    @Override
    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, clz.getSimpleName()).insertOrReplaceAll(hasteModelList);
    }

    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).insertOrReplaceAll(hasteModelList);
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
    public void deleteAll(Class<? extends HasteModel> clz)
    {
        getHasteDao(clz, clz.getSimpleName()).deleteAll(clz);
    }

    public void deleteAll(Class<? extends HasteModel> clz, String prefix, String suffix)
    {
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).deleteAll(clz);
    }

    @Override
    public void deleteAll(List<? extends HasteModel> hasteModelList)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, clz.getSimpleName()).deleteAll(hasteModelList);
    }

    public void deleteAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).deleteAll(hasteModelList);
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

    @Override
    public void run(String sql)
    {
        sqlExecutor.execSQL(sql);
    }

    public <T extends HasteModel> List<T> queryAll(Class<T> clz, String prefix, String suffix)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryAll(clz);
    }


    private static class HasteSQLiteOpenHelper extends SQLiteOpenHelper
    {
        private final IHasteConfig hasteConfig;

        private HasteSQLiteOpenHelper(Context context, IHasteConfig hasteConfig)
        {
            super(context, hasteConfig.dbName(), null, hasteConfig.dbVersion());
            this.hasteConfig = hasteConfig;
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            this.hasteConfig.onCreate(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            this.hasteConfig.onUpgrade(db, oldVersion, newVersion);
        }
    }
}
