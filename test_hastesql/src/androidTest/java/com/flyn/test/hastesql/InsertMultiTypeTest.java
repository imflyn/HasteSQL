package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.test.hastesql.entity.TestModel;

import java.util.LinkedList;
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


    }

    public void testInsert()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        TestModel testModel = new TestModel();
        hasteMaster.insert(testModel);

    }

    public void testInsertAll()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        TestModel testModel;
        List<TestModel> testModelList = new LinkedList<TestModel>();
        for (int i = 0; i < 10000; i++)
        {
            testModel = new TestModel();
            testModelList.add(testModel);
        }
        hasteMaster.insertAll(testModelList);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
