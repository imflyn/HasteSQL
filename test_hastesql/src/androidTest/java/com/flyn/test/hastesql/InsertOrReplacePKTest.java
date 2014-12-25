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
 * Created by flyn on 2014-11-28.
 */
public class InsertOrReplacePKTest extends AndroidTestCase
{
    private Context mContext;
    private List<People2> peopleList = new ArrayList<People2>();

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        HasteSQL.createDefault(mContext).deleteAll(People2.class);
        for (int i = 0; i < 10; i++)
        {
            People2 people = new People2();
            people.setUid(i + "");
            people.setAge(i);
            people.setDate(new Date());
            people.setName("第" + i + "号");
            peopleList.add(people);
        }
        HasteSQL.createDefault(mContext).insertAll(peopleList);
    }


    public void testInsertOrReplace()
    {
        People2 people = peopleList.get(0);
        people.setName("张三");

        People2 people2 = peopleList.get(1);
        people2.setName("李四");
        people2.setAge(888);

        HasteSQL.createDefault(mContext).insertOrReplace(people);
        HasteSQL.createDefault(mContext).insertOrReplace(people2);

        People2 people3 = new People2();
        people3.setUid("100");
        people3.setAge(100);
        people3.setDate(new Date());
        people3.setName("第" + 100 + "号");
        HasteSQL.createDefault(mContext).insertOrReplace(people3);
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
