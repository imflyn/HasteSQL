package com.flyn.test.hastesql;

import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.AutoIncreaseModel;
import com.flyn.test.hastesql.entity.People;

public class CreateTableTest extends AndroidTestCase
{

    public void testCreateTable()
    {
        AutoIncreaseModel autoIncreaseModel = new AutoIncreaseModel();
        autoIncreaseModel.setAge(2);
        autoIncreaseModel.setName("flyn");

        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        hasteMaster.insert(autoIncreaseModel);
    }

    public void testCreateTable2()
    {
        People people = new People();
        people.setAge(2);
        people.setName("flyn");

        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        hasteMaster.insert(people);
        LogUtils.i(HasteSQL.createDefault(mContext).queryAll(People.class).toString());
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
