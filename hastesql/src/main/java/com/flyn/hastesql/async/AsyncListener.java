package com.flyn.hastesql.async;

/**
 * Created by flyn on 2014-12-03.
 */
public abstract class AsyncListener<T>
{
    public void onStart()
    {

    }

    public void onFinish()
    {

    }

    public void onSuccess(T data)
    {

    }

    public void onFailure(Throwable e)
    {

    }

}
