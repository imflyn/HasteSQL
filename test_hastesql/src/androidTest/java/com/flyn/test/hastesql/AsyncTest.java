package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.async.AsyncListener;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.People;

import java.util.Date;

/**
 * Created by flyn on 2014-12-09.
 */
public class AsyncTest extends AndroidTestCase
{
    private Context mContext;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
    }

    public void testAsyncInsert() throws InterruptedException
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        People people = new People();
        people.setDate(new Date());
        people.setAge(3);
        people.setName("2号");

        hasteMaster.startAsync().insert(people, new AsyncListener<Object>()
        {
            @Override
            public void onStart()
            {
                LogUtils.d("onStart");
            }

            @Override
            public void onFinish()
            {
                LogUtils.d("onFinish");
            }

            @Override
            public void onSuccess(Object data)
            {
                LogUtils.d("onSuccess");
            }

            @Override
            public void onFailure(Throwable e)
            {
                LogUtils.d("onFailure");
            }
        });

        hasteMaster.startAsync().insert(people, new AsyncListener<Object>()
        {
            @Override
            public void onStart()
            {
                LogUtils.d("onStart");
            }

            @Override
            public void onFinish()
            {
                LogUtils.d("onFinish");
            }

            @Override
            public void onSuccess(Object data)
            {
                LogUtils.d("onSuccess");
            }

            @Override
            public void onFailure(Throwable e)
            {
                LogUtils.d("onFailure");
            }
        });


        while (true)
        {
            Thread.sleep(5000);
        }
    }

}
