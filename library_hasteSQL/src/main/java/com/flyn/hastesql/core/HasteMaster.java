package com.flyn.hastesql.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flyn.hastesql.optional.ConditionExpression;

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
    private SQLiteDatabase db;
    private SQLExecutor sqlExecutor;

    private HasteMaster(Context context, IHasteConfig hasteConfig)
    {
        init(context, hasteConfig);
    }

    public static HasteMaster newInstance(Context context, IHasteConfig hasteConfig)
    {
        return new HasteMaster(context, hasteConfig);
    }

    private void init(Context context, IHasteConfig hasteConfig)
    {
        this.hasteDaoMap.clear();
        HasteSQLiteOpenHelper hasteSQLiteOpenHelper = new HasteSQLiteOpenHelper(context, hasteConfig);
        this.db = hasteSQLiteOpenHelper.getWritableDatabase();
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

    public void insert(HasteModel hasteModel, String prefix, String suffix)
    {
        getHasteDao(hasteModel.getClass(), prefix + hasteModel.getClass().getSimpleName() + suffix).insert(hasteModel);
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
    public void updateAll(List<? extends HasteModel> hasteModelList)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, clz.getSimpleName()).updateAll(hasteModelList);
    }

    public void updateAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).updateAll(hasteModelList);
    }

    @Override
    public void update(Class<? extends HasteModel> clz, ConditionExpression valueExpression, ConditionExpression whereExpression)
    {
        getHasteDao(clz, clz.getSimpleName()).update(clz, valueExpression, whereExpression);
    }

    public void update(Class<? extends HasteModel> clz, String prefix, String suffix, ConditionExpression valueExpression,
                       ConditionExpression whereExpression)
    {
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).update(clz, valueExpression, whereExpression);
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
    public void delete(Class<? extends HasteModel> clz, ConditionExpression conditionExpression)
    {
        getHasteDao(clz, clz.getSimpleName()).delete(clz, conditionExpression);
    }

    public void delete(Class<? extends HasteModel> clz, String prefix, String suffix, ConditionExpression conditionExpression)
    {
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).delete(clz, conditionExpression);
    }

    @Override
    public void deleteAll(Class<? extends HasteModel> clz)
    {
        getHasteDao(clz, clz.getSimpleName()).deleteAll(clz);
    }

    @Override
    public void deleteAll(List<? extends HasteModel> hasteModelList)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, clz.getSimpleName()).deleteAll(hasteModelList);
    }

    public void deleteAll(Class<? extends HasteModel> clz, String prefix, String suffix)
    {
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).deleteAll(clz);
    }

    public void deleteAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).deleteAll(hasteModelList);
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

    @Override
    public <T extends HasteModel> List<T> query(Class<T> clz, ConditionExpression conditionExpression)
    {
        return getHasteDao(clz, clz.getSimpleName()).query(clz, conditionExpression);
    }

    public <T extends HasteModel> List<T> query(Class<T> clz, String prefix, String suffix, ConditionExpression conditionExpression)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).query(clz, conditionExpression);
    }

    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz, ConditionExpression conditionExpression)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryFirst(clz, conditionExpression);
    }

    public <T extends HasteModel> T queryFirst(Class<T> clz, String prefix, String suffix, ConditionExpression conditionExpression)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryFirst(clz, conditionExpression);
    }

    @Override
    public <T extends HasteModel> T queryByKey(Class<T> clz, Object key)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryByKey(clz, key);
    }

    public <T extends HasteModel> T queryByKey(Class<T> clz, String prefix, String suffix, Object key)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryByKey(clz, key);
    }

    @Override
    public void run(String sql)
    {
        sqlExecutor.execSQL(sql);
    }

    @Override
    public void run(String sql, Object[] args)
    {
        sqlExecutor.execSQL(sql, args);
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
