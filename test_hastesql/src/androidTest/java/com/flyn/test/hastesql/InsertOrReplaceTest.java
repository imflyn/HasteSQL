package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
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
        for (int i = 0; i < 20; i++)
        {
            People people = new People();
            people.setAge(i);
            people.setDate(new Date());
            people.setName("第" + i + "号");
            peopleList.add(people);
        }
        HasteSQL.createDefault(mContext).insertAll(peopleList);
    }


    public void testInsertOrReplace()
    {
        People people = peopleList.get(0);
        people.setName("张三");

        People people2=new People();
        people2.setName("李四");
        people2.setAge(888);

        HasteSQL.createDefault(mContext).insertOrReplace(people);
        HasteSQL.createDefault(mContext).insertOrReplace(people2);
    }
}
