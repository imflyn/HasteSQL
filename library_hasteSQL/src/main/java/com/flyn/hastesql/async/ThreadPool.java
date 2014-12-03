package com.flyn.hastesql.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by flyn on 2014-12-03.
 */
public class ThreadPool
{
    private ExecutorService executorService;

    private ThreadPool()
    {
        this.executorService = Executors.newCachedThreadPool(new ThreadFactory()
        {
            private AtomicInteger atomicInteger = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r)
            {
                Thread thread = new Thread();
                thread.setName("HasteSQL Thread#" + atomicInteger.incrementAndGet());
                return thread;
            }
        });
    }

    private ThreadPool(ExecutorService executorService)
    {
        this.executorService = executorService;
    }

    public static ThreadPool createDefault()
    {
        return new ThreadPool();
    }

    public static ThreadPool createNew(ExecutorService executorService)
    {
        return new ThreadPool(executorService);
    }


}
