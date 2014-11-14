package com.flyn.test.hastesql;

import android.content.Context;
import android.os.SystemClock;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.People;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by flyn on 2014-11-12.
 */
public class InsertTest extends AndroidTestCase
{

    private Context mContext;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        assertNotNull(mContext);


    }

    public void testInsert()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        People people = new People();
        hasteMaster.insert(people);

    }

    public void testInsertAll()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        People people;
        List<People> testMultiModelList = new LinkedList<People>();
        for (int i = 0; i < 10000; i++)
        {
            people = new People();
            testMultiModelList.add(people);
        }
        long time = SystemClock.uptimeMillis();
        hasteMaster.insertAll(testMultiModelList);
        LogUtils.d("花费时间:" + (SystemClock.uptimeMillis() - time));
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
