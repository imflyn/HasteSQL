package com.flyn.test.hastesql;

import android.content.Context;
import android.os.SystemClock;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.TestMultiModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyn on 2014-11-12.
 */
public class InsertMultiTypeTest extends AndroidTestCase
{

    private Context mContext;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        assertNotNull(mContext);

        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        TestMultiModel testMultiModel;
        List<TestMultiModel> testMultiModelList = new ArrayList<TestMultiModel>();
        for (int i = 0; i < 10000; i++)
        {
            testMultiModel = new TestMultiModel();
            testMultiModelList.add(testMultiModel);
        }
        long time = SystemClock.uptimeMillis();

        hasteMaster.insertAll(testMultiModelList);

        LogUtils.d("花费时间:" + (SystemClock.uptimeMillis() - time));
    }

    public void testQuery()
    {
        long time = System.currentTimeMillis();
        List<TestMultiModel> list = HasteSQL.createDefault(mContext).queryAll(TestMultiModel.class);

        LogUtils.d("花费时间:" + (System.currentTimeMillis() - time));
//        for (TestMultiModel entity : list)
//        {
//            LogUtils.i(entity.toString());
//        }

    }

    public void testInsert()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        TestMultiModel testMultiModel = new TestMultiModel();
        hasteMaster.insert(testMultiModel);

    }

    public void testInsertAll()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        TestMultiModel testMultiModel;
        List<TestMultiModel> testMultiModelList = new ArrayList<TestMultiModel>();
        for (int i = 0; i < 10000; i++)
        {
            testMultiModel = new TestMultiModel();
            testMultiModelList.add(testMultiModel);
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
