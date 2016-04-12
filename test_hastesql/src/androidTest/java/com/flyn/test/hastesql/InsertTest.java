package com.flyn.test.hastesql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.core.IHasteConfig;
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

    private Context      mContext;
    private IHasteConfig iHasteConfig;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
        assertNotNull(mContext);

        iHasteConfig = new IHasteConfig()
        {
            @Override
            public void onCreate(SQLiteDatabase db)
            {

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
            {

            }

            @Override
            public int dbVersion()
            {
                return 1;
            }

            @Override
            public String dbName()
            {
                return "test-Multi";
            }
        };
    }

    public void testInsert()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        People people = new People();
        people.setDate(new Date());
        people.setAge(3);
        people.setName("2号");
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

    public void testMultiDb()
    {
        HasteMaster hasteMaster = HasteSQL.createNew(mContext, iHasteConfig);

        People people = new People();
        hasteMaster.insert(people);

    }

    public void testMultiTable()
    {
        HasteMaster hasteMaster = HasteSQL.createNew(mContext, iHasteConfig);

        People people = new People();
        hasteMaster.insert(people, "test", "2");
    }

    public void testInsertOrReplace()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);

        People people = new People();
        people.setId(2);
        people.setDate(new Date());
        people.setAge(22);
        people.setName("1号");
        hasteMaster.insertOrReplace(people);
    }

    public void testInsertOrReplaceAll()
    {
        HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
        Note note;
        List<Note> testMultiModelList = new ArrayList<Note>();
        for (int i = 0; i < 2; i++)
        {
            note = new Note(Long.valueOf(i + ""), "123", "466", new Date());
            testMultiModelList.add(note);
        }
        long time = SystemClock.uptimeMillis();
        hasteMaster.insertOrReplaceAll(testMultiModelList);
        LogUtils.d("花费时间:" + (SystemClock.uptimeMillis() - time));
    }

    public void testMultiThreadInsert() throws InterruptedException
    {
        for (int j = 0; j < 20; j++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {

                    HasteMaster hasteMaster = HasteSQL.createDefault(mContext);
                    Note note;
                    List<Note> testMultiModelList = new ArrayList<Note>();
                    for (int i = 0; i < 10; i++)
                    {
                        note = new Note();
                        note.setDate(new Date());
                        note.setComment("Comment");
                        note.setText("text");
                        testMultiModelList.add(note);
                    }
                    long time = SystemClock.uptimeMillis();
                    hasteMaster.insertAll(testMultiModelList);
                    LogUtils.d(Thread.currentThread().getName() + "==花费时间:" + (SystemClock.uptimeMillis() - time));
                }
            }).start();

        }

        while (true)
        {
            Thread.sleep(5000);
        }
    }


    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
}
