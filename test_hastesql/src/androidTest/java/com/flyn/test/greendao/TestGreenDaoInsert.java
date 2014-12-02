package com.flyn.test.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.flyn.hastesql.util.LogUtils;
import com.flyn.test.greendao.entity.DaoMaster;
import com.flyn.test.greendao.entity.DaoSession;
import com.flyn.test.greendao.entity.Note;
import com.flyn.test.greendao.entity.NoteDao;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by flyn on 2014-11-14.
 */
public class TestGreenDaoInsert extends AndroidTestCase
{
    private Context mContext;
    private SQLiteDatabase db;


    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        mContext = getContext();
    }

    public void testInsertAll()
    {
        DaoMaster daoMaster;
        DaoSession daoSession;
        NoteDao noteDao;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "greendao-db", null);

        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        noteDao = daoSession.getNoteDao();


        long time = System.currentTimeMillis();

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());
        List<Note> list = new ArrayList<Note>();
        Note note;
        for (int i = 0; i < 10000; i++)
        {
            note = new Note(null, "123", comment, new Date());
            list.add(note);
        }
        noteDao.insertOrReplaceInTx(list);

        long spendTime = System.currentTimeMillis() - time;

        LogUtils.d("花费时间：" + spendTime);


    }


}
