package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.test.hastesql.entity.People;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-18.
 */
public class DeleteTest extends AndroidTestCase
{

    private Context mContext;
    private List<People> peopleList = new ArrayList<People>();

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();

        for (int i = 0; i < 1000; i++)
        {
            People people = new People();
            people.setAge(19);
            people.setDate(new Date());
            people.setName(i + "");
            peopleList.add(people);


        }
        HasteSQL.createDefault(mContext).insertAll(peopleList);
    }


    public void testDeleteAll()
    {
        HasteSQL.createDefault(mContext).deleteAll(People.class);

    }

}
