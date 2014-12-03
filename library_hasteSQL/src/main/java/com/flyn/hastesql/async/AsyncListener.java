package com.flyn.hastesql.async;

/**
 * Created by flyn on 2014-12-03.
 */
public interface AsyncListener<T>
{
    void onStart();

    void onFinish();

    void onSuccess(T data);

    void onFailed(Exception e);

    void onCancel();
}
