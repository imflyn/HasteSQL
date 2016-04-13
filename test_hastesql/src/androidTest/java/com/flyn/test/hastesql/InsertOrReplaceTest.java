package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.People;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-28.
 */
public class InsertOrReplaceTest extends AndroidTestCase
{
    private Context mContext;
    private List<People> peopleList = new ArrayList<People>();

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        HasteSQL.createDefault(mContext).deleteAll(People.class);
        for (int i = 0; i < 10; i++)
        {
            People people = new People();
            people.setAge(i);
            people.setDate(new Date());
            people.setName("第" + i + "号");
            peopleList.add(people);
        }
        HasteSQL.createDefault(mContext).insertAll(peopleList);

        List<People> peoples = HasteSQL.createDefault(mContext).queryAll(People.class);
        LogUtils.i(peoples.toString());
    }


    public void testInsertOrReplace()
    {
        People people = peopleList.get(0);
        people.setName("张三");

        People people2 = peopleList.get(1);
        people2.setName("李四");
        people2.setAge(888);

        HasteSQL.createDefault(mContext).insertOrReplace(people);
        HasteSQL.createDefault(mContext).insertOrReplace(people2);

        People people3 = new People();
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
        peopleList.addAll(HasteSQL.createDefault(mContext).queryAll(People.class));

        for (People people : peopleList)
        {
            LogUtils.d(people.toString());
        }
    }
}
