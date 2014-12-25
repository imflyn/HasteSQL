package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.update.People2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-12-25.
 */
public class UpdateHasPKTest extends AndroidTestCase
{

    private Context mContext;
    private List<People2> peopleList = new ArrayList<People2>();

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        HasteSQL.createDefault(mContext).deleteAll(People2.class);
        for (int i = 0; i < 20; i++)
        {
            People2 people = new People2();
            people.setAge(i);
            people.setDate(new Date());
            people.setName("第" + i + "号");
            people.setUid("uid:" + i);
            peopleList.add(people);
        }
        HasteSQL.createDefault(mContext).insertAll(peopleList);
    }


    public void testUpdate()
    {
        People2 people = peopleList.get(1);
        people.setAge(777);
        people.setDate(new Date());
        people.setName("张三");
        people.setUid("uid:" + 0);

        HasteSQL.createDefault(mContext).update(people);

        People2 people2 = peopleList.get(0);
        people2.setAge(777);
        people2.setDate(new Date());
        people2.setName("张三");
        people2.setUid("uid:" + 1);

        HasteSQL.createDefault(mContext).update(people2);

    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        peopleList.clear();
        peopleList.addAll(HasteSQL.createDefault(mContext).queryAll(People2.class));

        for (People2 people : peopleList)
        {
            LogUtils.d(people.toString());
        }
    }
}
