package com.flyn.test.hastesql;

import android.content.Context;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.MultiHasteModel;
import com.flyn.test.hastesql.entity.Name;
import com.flyn.test.hastesql.entity.People;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2016/4/23.
 */
public class MultiHasteModelTest extends AndroidTestCase
{

    private Context     mContext;
    private HasteMaster hasteMaster;
    private List<MultiHasteModel> multiHasteModelList = new ArrayList<>();

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        hasteMaster = HasteSQL.createDefault(mContext);

        for (int i = 0; i < 1; i++)
        {
            MultiHasteModel multiHasteModel = new MultiHasteModel();
            multiHasteModel.setName("第" + i + "个");

            List<Name> nameList = new ArrayList<>();
            for (int j = 0; j < 2; j++)
            {
                Name name = new Name();
                name.setLocale("locale:" + j);
                name.setMessage(j + "message");
                nameList.add(name);
            }

            multiHasteModel.setNameList(nameList);

            People people = new People();
            people.setName("flyn" + i);
            people.setId(i);
            people.setAge(i);
            people.setDate(new Date());

            People[] peopleArray = new People[]{people, people};

            multiHasteModel.setPeopleArray(peopleArray);
            multiHasteModelList.add(multiHasteModel);
        }

    }

    public void testInsert()
    {
        hasteMaster.insertAll(multiHasteModelList);
        LogUtils.i(hasteMaster.queryAll(MultiHasteModel.class).toString());
    }
}
