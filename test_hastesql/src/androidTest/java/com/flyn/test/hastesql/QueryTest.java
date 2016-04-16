package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.optional.ConditionBuilder;
import com.flyn.hastesql.optional.ConditionExpression;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.People;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-28.
 */
public class QueryTest extends AndroidTestCase
{

    private Context mContext;
    private List<People> peopleList = new ArrayList<People>();

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        //        HasteSQL.createDefault(mContext).deleteAll(People.class);
        for (int i = 0; i < 10000; i++)
        {
            People people = new People();
            people.setAge(i);
            people.setDate(new Date());
            people.setName("第" + i + "号");
            peopleList.add(people);
        }
        HasteSQL.createDefault(mContext).insertAll(peopleList);
    }

    public void testQueryAll()
    {
        long time = System.currentTimeMillis();
        List<People> list = HasteSQL.createDefault(mContext).queryAll(People.class);

        LogUtils.d("花费时间:" + (System.currentTimeMillis() - time));
        LogUtils.d("共" + list.size() + "条记录");
        //        for (People entity : list)
        //        {
        //            LogUtils.i(entity.toString());
        //        }

    }

    public void testQueryByKey()
    {
        long time = System.currentTimeMillis();
        People entity = HasteSQL.createDefault(mContext).queryByKey(People.class, 1);

        LogUtils.d("花费时间:" + (System.currentTimeMillis() - time));
        LogUtils.i(entity.toString());
    }

    public void testQueryFirst()
    {
        long time = System.currentTimeMillis();

        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.equals("id", 2);

        ConditionBuilder conditionBuilder = new ConditionBuilder();
        conditionBuilder.where(conditionExpression);

//        People entity = HasteSQL.createDefault(mContext).queryFirst(People.class, conditionBuilder);
        People entity = HasteSQL.createDefault(mContext).queryFirst(People.class);

        LogUtils.d("花费时间:" + (System.currentTimeMillis() - time));
        LogUtils.i(entity.toString());


    }

    public void testQueryCondition()
    {
        long time = System.currentTimeMillis();
        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.greater("age", 2);
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        conditionBuilder.where(conditionExpression).orderByDesc("age");

        List<People> list = HasteSQL.createDefault(mContext).query(People.class, conditionBuilder);
        LogUtils.d("花费时间:" + (System.currentTimeMillis() - time));
        //        for (People entity : list)
        //        {
        //            LogUtils.i(entity.toString());
        //        }

    }

}
