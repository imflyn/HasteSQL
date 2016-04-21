package com.flyn.test.hastesql;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by flyn on 2016/4/21.
 */
public class TestApplication extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
