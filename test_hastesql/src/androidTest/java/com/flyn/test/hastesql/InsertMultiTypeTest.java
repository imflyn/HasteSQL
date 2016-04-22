package com.flyn.test.hastesql;

import android.content.Context;
import android.os.SystemClock;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.Bill;
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

        //        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        //        TestMultiModel testMultiModel;
        //        List<TestMultiModel> testMultiModelList = new ArrayList<TestMultiModel>();
        //        for (int i = 0; i < 10000; i++)
        //        {
        //            testMultiModel = new TestMultiModel();
        //            testMultiModelList.add(testMultiModel);
        //        }
        //        long time = SystemClock.uptimeMillis();
        //
        //        hasteMaster.insertAll(testMultiModelList);
        //
        //        LogUtils.d("花费时间:" + (SystemClock.uptimeMillis() - time));
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

    public void testListInsert()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        Bill order = new Bill();


        order.orderId = String.valueOf(System.currentTimeMillis());
        order.messageList = new ArrayList<>();
        Bill.Message message = new Bill.Message("1");
        Bill.Message message2 = new Bill.Message("2");
        order.messageList.add(message);
        order.messageList.add(message2);
        order.users = new Bill.User[]{new Bill.User("flyn"), new Bill.User("dq")};

        hasteMaster.insert(order);

        LogUtils.i(hasteMaster.queryAll(Bill.class).toString());
    }


}
