package com.flyn.hastesql.async;

import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.core.HasteModel;
import com.flyn.hastesql.optional.ConditionBuilder;
import com.flyn.hastesql.optional.ConditionExpression;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by flyn on 2014-11-10.
 */
public class Async
{

    private final HasteMaster hasteMaster;
    private final ThreadPool threadPool;


    private Async(HasteMaster hasteMaster)
    {
        this.hasteMaster = hasteMaster;
        this.threadPool = ThreadPool.createDefault();
    }

    private Async(HasteMaster hasteMaster, ThreadPool threadPool)
    {
        this.hasteMaster = hasteMaster;
        this.threadPool = threadPool;
    }

    public static Async create(HasteMaster hasteMaster)
    {
        Async async = new Async(hasteMaster);
        return async;
    }

    public static Async create(HasteMaster hasteMaster, ThreadPool threadPool)
    {
        Async async = new Async(hasteMaster, threadPool);
        return async;
    }

    public void insert(HasteModel hasteModel)
    {
        insert(hasteModel, null);
    }

    public void insert(HasteModel hasteModel, String prefix, String suffix)
    {
        insert(hasteModel, prefix, suffix, null);
    }

    public void insert(HasteModel hasteModel, AsyncListener<?> asyncListener)
    {
        if (null != asyncListener)
        {
            asyncListener.onStart();
        }

        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                return null;
            }
        });


    }

    public void insert(HasteModel hasteModel, String prefix, String suffix, AsyncListener<?> asyncListener)
    {
        hasteMaster.insert(hasteModel, prefix, suffix);
    }

    public void insertAll(List<? extends HasteModel> hasteModelList)
    {
    }

    public void insertAll(List<HasteModel> hasteModelList, String prefix, String suffix)
    {
    }


    public void update(HasteModel hasteModel)
    {
    }


    public void update(HasteModel hasteModel, String prefix, String suffix)
    {
    }

    public void updateAll(List<? extends HasteModel> hasteModelList)
    {
    }

    public void updateAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
    }

    public void update(Class<? extends HasteModel> clz, ConditionExpression valueExpression, ConditionExpression whereExpression)
    {
    }

    public void update(Class<? extends HasteModel> clz, String prefix, String suffix, ConditionExpression valueExpression,
                       ConditionExpression whereExpression)
    {
    }

    public void insertOrReplace(HasteModel hasteModel)
    {
    }

    public void insertOrReplace(HasteModel hasteModel, String prefix, String suffix)
    {
    }

    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList)
    {
    }

    public void insertOrReplaceAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
    }


    public void delete(HasteModel hasteModel)
    {
    }

    public void delete(HasteModel hasteModel, String prefix, String suffix)
    {
    }

    public void delete(Class<? extends HasteModel> clz, ConditionExpression conditionExpression)
    {
    }

    public void delete(Class<? extends HasteModel> clz, String prefix, String suffix, ConditionExpression conditionExpression)
    {
    }

    public void deleteAll(Class<? extends HasteModel> clz)
    {
    }

    public void deleteAll(List<? extends HasteModel> hasteModelList)
    {
    }

    public void deleteAll(Class<? extends HasteModel> clz, String prefix, String suffix)
    {
    }

    public void deleteAll(List<? extends HasteModel> hasteModelList, String prefix, String suffix)
    {
    }

    public <T extends HasteModel> void queryAll(Class<T> clz)
    {
    }

    public <T extends HasteModel> void queryAll(Class<T> clz, String prefix, String suffix)
    {
    }

    public <T extends HasteModel> void query(Class<T> clz, ConditionBuilder conditionBuilder)
    {
    }

    public <T extends HasteModel> void query(Class<T> clz, String prefix, String suffix, ConditionBuilder conditionBuilder)
    {
    }

    public <T extends HasteModel> void queryFirst(Class<T> clz, ConditionBuilder conditionBuilder)
    {
    }

    public <T extends HasteModel> void queryFirst(Class<T> clz, String prefix, String suffix, ConditionBuilder conditionBuilder)
    {
    }

    public <T extends HasteModel> void queryByKey(Class<T> clz, Object key)
    {
    }

    public <T extends HasteModel> void queryByKey(Class<T> clz, String prefix, String suffix, Object key)
    {
    }


    public void run(String sql)
    {
    }

    public void run(String sql, Object[] args)
    {
    }

    public void query(String sql, String[] args)
    {

    }


}
