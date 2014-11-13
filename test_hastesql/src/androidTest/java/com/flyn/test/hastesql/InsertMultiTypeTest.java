package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.test.hastesql.entity.TestMultiModel;

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

        TestMultiModel testMultiModel = new TestMultiModel();
        hasteMaster.insert(testMultiModel);

    }

    public void testInsertAll()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        TestMultiModel testMultiModel;
        List<TestMultiModel> testMultiModelList = new LinkedList<TestMultiModel>();
        for (int i = 0; i < 10000; i++)
        {
            testMultiModel = new TestMultiModel();
            testMultiModelList.add(testMultiModel);
        }
        hasteMaster.insertAll(testMultiModelList);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
