package com.flyn.hastesql.core;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.flyn.hastesql.async.Async;
import com.flyn.hastesql.async.ThreadPool;
import com.flyn.hastesql.optional.ConditionBuilder;
import com.flyn.hastesql.optional.ConditionExpression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * Created by flyn on 2014-11-11.
 * Thread-Safe
 */
public class HasteMaster implements HasteOperation
{
    private final Map<String, HasteDao> hasteDaoMap = new HashMap<>();
    private SQLiteDatabase db;
    private SQLExecutor    sqlExecutor;
    private Async          async;

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
        HasteSQLiteOpenHelper hasteSQLiteOpenHelper = new HasteSQLiteOpenHelper(context, hasteConfig);
        this.db = hasteSQLiteOpenHelper.getWritableDatabase();
        this.sqlExecutor = new SQLExecutor(this.db);
    }

    private synchronized HasteDao getHasteDao(Class<? extends HasteModel> clz, String tableName)
    {
        HasteDao hasteDao = this.hasteDaoMap.get(tableName);
        if (null == hasteDao)
        {
            hasteDao = new HasteDao(this.sqlExecutor, tableName, clz);
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

    public void update(Class<? extends HasteModel> clz, ConditionBuilder conditionBuilder)
    {
        getHasteDao(clz, clz.getSimpleName()).update(conditionBuilder);
    }

    public void update(Class<? extends HasteModel> clz, String prefix, String suffix, ConditionBuilder conditionBuilder)
    {
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).update(conditionBuilder);
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
        getHasteDao(clz, clz.getSimpleName()).delete(conditionExpression);
    }

    public void delete(Class<? extends HasteModel> clz, String prefix, String suffix, ConditionExpression conditionExpression)
    {
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).delete(conditionExpression);
    }

    @Override
    public void deleteAll(Class<? extends HasteModel> clz)
    {
        getHasteDao(clz, clz.getSimpleName()).deleteAll();
    }

    @Override
    public void deleteAll(List<? extends HasteModel> hasteModelList)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, clz.getSimpleName()).deleteAll(hasteModelList);
    }

    public void deleteAll(Class<? extends HasteModel> clz, String prefix, String suffix)
    {
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).deleteAll();
    }

    public void deleteAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
        Class<? extends HasteModel> clz = hasteModelList.get(0).getClass();
        getHasteDao(clz, prefix + clz.getSimpleName() + suffix).deleteAll(hasteModelList);
    }

    @Override
    public <T extends HasteModel> List<T> queryAll(Class<T> clz)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryAll();
    }

    public <T extends HasteModel> List<T> queryAll(Class<T> clz, String prefix, String suffix)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryAll();
    }

    @Override
    public <T extends HasteModel> List<T> query(Class<T> clz, ConditionBuilder conditionBuilder)
    {
        return getHasteDao(clz, clz.getSimpleName()).query(conditionBuilder);
    }

    public <T extends HasteModel> List<T> query(Class<T> clz, String prefix, String suffix, ConditionBuilder conditionBuilder)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).query(conditionBuilder);
    }

    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryFirst();
    }

    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz, ConditionBuilder conditionBuilder)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryFirst(conditionBuilder);
    }

    public <T extends HasteModel> T queryFirst(Class<T> clz, String prefix, String suffix, ConditionBuilder conditionBuilder)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryFirst(conditionBuilder);
    }

    @Override
    public <T extends HasteModel> T queryByKey(Class<T> clz, Object key)
    {
        return getHasteDao(clz, clz.getSimpleName()).queryByKey(key);
    }

    public <T extends HasteModel> T queryByKey(Class<T> clz, String prefix, String suffix, Object key)
    {
        return getHasteDao(clz, prefix + clz.getSimpleName() + suffix).queryByKey(key);
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

    @Override
    public Cursor query(String sql, String[] args)
    {
        return sqlExecutor.execQuery(sql, args);
    }


    public synchronized Async startAsync()
    {
        if (async == null)
        {
            async = Async.create(this);
        }
        return async;
    }

    public synchronized Async startAsync(ExecutorService executorService)
    {
        if (async == null)
        {
            async = Async.create(this, ThreadPool.createNew(executorService));
        }
        return async;
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
