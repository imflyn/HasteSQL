package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.test.hastesql.entity.People;

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
        people.setName("张三");
        people.setAge(18);
        hasteMaster.insert(people);


    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
