package com.flyn.hastesql.async;

import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

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
    private final ExecutorDelivery executorDelivery;


    private Async(HasteMaster hasteMaster)
    {
        this(hasteMaster, null);
    }

    private Async(HasteMaster hasteMaster, ThreadPool threadPool)
    {
        this.hasteMaster = hasteMaster;
        this.threadPool = threadPool == null ? ThreadPool.createDefault() : threadPool;
        this.executorDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
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

    public void insert(final HasteModel hasteModel, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insert(hasteModel);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return null;
            }
        });
    }

    public void insert(final HasteModel hasteModel, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insert(hasteModel, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void insertAll(final List<? extends HasteModel> hasteModelList, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insertAll(hasteModelList);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void insertAll(final List<HasteModel> hasteModelList, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insertAll(hasteModelList, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void update(final HasteModel hasteModel, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.update(hasteModel);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void update(final HasteModel hasteModel, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.update(hasteModel, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void updateAll(final List<? extends HasteModel> hasteModelList, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.updateAll(hasteModelList);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void updateAll(final List<? extends HasteModel> hasteModelList, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.updateAll(hasteModelList, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void update(final Class<? extends HasteModel> clz, final ConditionExpression valueExpression, final ConditionExpression whereExpression,
                       final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.update(clz, valueExpression, whereExpression);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void update(final Class<? extends HasteModel> clz, final String prefix, final String suffix, final ConditionExpression valueExpression,
                       final ConditionExpression whereExpression, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.update(clz, prefix, suffix, valueExpression, whereExpression);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void insertOrReplace(final HasteModel hasteModel, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insertOrReplace(hasteModel);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void insertOrReplace(final HasteModel hasteModel, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insertOrReplace(hasteModel, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void insertOrReplaceAll(final List<? extends HasteModel> hasteModelList, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insertOrReplaceAll(hasteModelList);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void insertOrReplaceAll(final List<? extends HasteModel> hasteModelList, final String prefix, final String suffix,
                                   final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.insertOrReplaceAll(hasteModelList, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }


    public void delete(final HasteModel hasteModel, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.delete(hasteModel);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void delete(final HasteModel hasteModel, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.delete(hasteModel, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void delete(final Class<? extends HasteModel> clz, final ConditionExpression conditionExpression, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.delete(clz, conditionExpression);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void delete(final Class<? extends HasteModel> clz, final String prefix, final String suffix,
                       final ConditionExpression conditionExpression, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.delete(clz, prefix, suffix, conditionExpression);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void deleteAll(final Class<? extends HasteModel> clz, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.deleteAll(clz);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void deleteAll(final List<? extends HasteModel> hasteModelList, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.deleteAll(hasteModelList);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void deleteAll(final Class<? extends HasteModel> clz, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.deleteAll(clz, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void deleteAll(final List<? extends HasteModel> hasteModelList, final String prefix, final String suffix, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.deleteAll(hasteModelList, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void queryAll(final Class<T> clz, final AsyncListener<List<T>> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    List<T> result = hasteMaster.queryAll(clz);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void queryAll(final Class<T> clz, final String prefix, final String suffix, final AsyncListener<List<T>> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    List<T> result = hasteMaster.queryAll(clz, prefix, suffix);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void query(final Class<T> clz, final ConditionBuilder conditionBuilder, final AsyncListener<List<T>> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    List<T> result = hasteMaster.query(clz, conditionBuilder);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void query(final Class<T> clz, final String prefix, final String suffix, final ConditionBuilder conditionBuilder,
                                             final AsyncListener<List<T>> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    List<T> result = hasteMaster.query(clz, prefix, suffix, conditionBuilder);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void queryFirst(final Class<T> clz, final ConditionBuilder conditionBuilder, final AsyncListener<T> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    T result = hasteMaster.queryFirst(clz, conditionBuilder);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void queryFirst(final Class<T> clz, final String prefix, final String suffix,
                                                  final ConditionBuilder conditionBuilder, final AsyncListener<T> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    T result = hasteMaster.queryFirst(clz, prefix, suffix, conditionBuilder);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void queryByKey(final Class<T> clz, final Object key, final AsyncListener<T> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    T result = hasteMaster.queryByKey(clz, key);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public <T extends HasteModel> void queryByKey(final Class<T> clz, final String prefix, final String suffix, final Object key,
                                                  final AsyncListener<T> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    T result = hasteMaster.queryByKey(clz, prefix, suffix, key);
                    executorDelivery.sendSuccessMessage(listener, result);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }


    public void run(final String sql, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.run(sql);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void run(final String sql, final Object[] args, final AsyncListener<?> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    hasteMaster.run(sql, args);
                    executorDelivery.sendSuccessMessage(listener, null);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }

    public void query(final String sql, final String[] args, final AsyncListener<Cursor> listener)
    {
        threadPool.submit(new Callable<Object>()
        {
            @Override
            public Object call() throws Exception
            {
                executorDelivery.sendStartMessage(listener);
                try
                {
                    Cursor cursor = hasteMaster.query(sql, args);
                    executorDelivery.sendSuccessMessage(listener, cursor);
                } catch (Exception e)
                {
                    executorDelivery.sendFailureMessage(listener, e);
                } finally
                {
                    executorDelivery.sendFinishMessage(listener);
                }
                return true;
            }
        });
    }


}
