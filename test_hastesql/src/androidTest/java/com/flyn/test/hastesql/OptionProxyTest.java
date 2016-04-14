package com.flyn.test.hastesql;

import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.test.hastesql.entity.People;

import java.util.Date;

/**
 * Created by V on 2016/4/14.
 */
public class OptionProxyTest extends AndroidTestCase
{

    public void testUpdate()
    {
        final HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        People people = new People();
        people.setDate(new Date());
        people.setAge(3);
        people.setName("2号");

        hasteMaster.update1(People.class).where().equals("age", 3);

    }
}
