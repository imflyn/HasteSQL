package com.flyn.test.hastesql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.flyn.hastesql.HasteSQL;
import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.core.IHasteConfig;
import com.flyn.hastesql.util.CursorUtils;
import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.hastesql.entity.People;

import java.util.Date;

/**
 * Created by flyn on 2014-12-03.
 */
public class DBUpdateGradeTest extends AndroidTestCase
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
        IHasteConfig iHasteConfig = new IHasteConfig()
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

        HasteMaster hasteMaster = HasteSQL.createNew(mContext, iHasteConfig);

        People people = new People();
        people.setDate(new Date());
        people.setAge(1);
        people.setName("1号");
        hasteMaster.insert(people, "test", "2");
    }

    public void testUpdateGrade()
    {
        IHasteConfig hasteConfig = new IHasteConfig()
        {
            @Override
            public void onCreate(SQLiteDatabase db)
            {

            }


            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
            {
                LogUtils.d("oldVersion:" + oldVersion);
                LogUtils.d("newVersion:" + newVersion);

                switch (newVersion)
                {
                    case 3:
                        if (!CursorUtils.checkColumnExist(db, "testPeople2", "aa"))
                            db.execSQL(" ALTER TABLE testPeople2 add  aa TEXT ");
                        if (!CursorUtils.checkColumnExist(db, "testPeople2", "bb"))
                            db.execSQL(" ALTER TABLE testPeople2 add  bb TEXT ");
                    case 2:
                        if (!CursorUtils.checkColumnExist(db, "testPeople2", "isMan"))
                            db.execSQL(" ALTER TABLE testPeople2 add  isMan boolean ");


                }

            }

            @Override
            public int dbVersion()
            {
                return 3;
            }

            @Override
            public String dbName()
            {
                return "test-Multi";
            }
        };


        HasteMaster hasteMaster = HasteSQL.createNew(mContext, hasteConfig);

        com.flyn.test.hastesql.entity.update.People people = new com.flyn.test.hastesql.entity.update.People();
        people.setDate(new Date());
        people.setAge(3);
        people.setName("3号");
        people.setMan(true);
        people.setAa("aa");
        people.setBb("bb");
        hasteMaster.insert(people, "test", "2");

    }
}
