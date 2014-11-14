package com.flyn.test.hastesql;

import android.content.Context;
import android.os.SystemClock;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.greendao.entity.Note;
import com.flyn.test.hastesql.entity.People;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-12.
 */
public class InsertTest extends AndroidTestCase
{

    private Context mContext;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        assertNotNull(mContext);


    }

    public void testInsert()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        People people = new People();
        hasteMaster.insert(people);

    }

    public void testInsertAll()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        Note note;
        List<Note> testMultiModelList = new ArrayList<Note>();
        for (int i = 0; i < 10000; i++)
        {
            note = new Note(Long.valueOf(i + ""), "123", "466", new Date());
            testMultiModelList.add(note);
        }
        long time = SystemClock.uptimeMillis();
        hasteMaster.insertAll(testMultiModelList);
        LogUtils.d("花费时间:" + (SystemClock.uptimeMillis() - time));
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
