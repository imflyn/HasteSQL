package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.optional.ConditionExpression;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.People;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-27.
 */
public class UpdateTest extends AndroidTestCase
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


    public void testUpdate()
    {
        People people = peopleList.get(1);
        people.setAge(777);
        people.setDate(new Date());
        people.setName("张三");

        HasteSQL.createDefault(mContext).update(people);

    }

    public void testUpdateList()
    {
        People people1 = peopleList.get(0);
        people1.setAge(999);
        people1.setDate(new Date());
        people1.setName("张三");
        People people2 = peopleList.get(1);
        people2.setAge(888);
        people2.setDate(new Date());
        people2.setName("李四");

        long time = System.currentTimeMillis();
        HasteSQL.createDefault(mContext).updateAll(peopleList);
        LogUtils.d("花费时间:" + (System.currentTimeMillis() - time));
    }

    public void testUpdateWithCondition()
    {
        long time = System.currentTimeMillis();
        ConditionExpression valueExpression = new ConditionExpression();
        valueExpression.equals("age", 1).combine(",").equals("name", "sb");

        ConditionExpression whereExpression = new ConditionExpression();
        whereExpression.equals("age", 8).or().greater("age", 18);

        HasteSQL.createDefault(mContext).update(People.class, valueExpression, whereExpression);
        LogUtils.d("花费时间:" + (System.currentTimeMillis() - time));
    }

    public void testSQL()
    {
        String sql = "UPDATE People SET name= ? , date= ? , id= ? , age= ? WHERE id= ?";
        HasteSQL.createDefault(mContext).run(sql, new Object[]{"张三", new Date(), peopleList.get(0).getId(), 888, peopleList.get(0).getId()});
    }
}
