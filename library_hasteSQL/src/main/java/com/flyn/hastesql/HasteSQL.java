package com.flyn.hastesql;

import android.content.Context;

import com.flyn.hastesql.core.HasteMaster;
import com.flyn.hastesql.core.HasteModel;
import com.flyn.hastesql.core.HasteOperation;
import com.flyn.hastesql.core.IHasteDaoImpl;

import java.util.List;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Thread-Safe
 */
public class HasteSQL implements HasteOperation
{
    private final Context mContext;
    private final HasteMaster hasteMaster;
    private static HasteSQL hasteSQL;

    private HasteSQL(Context context)
    {
        hasteMaster = HasteMaster.newInstence();
        mContext = context.getApplicationContext();

    }

    private static synchronized HasteSQL getInstance(Context context)
    {
        if (null == hasteSQL)
        {
            hasteSQL = new HasteSQL(context);

        }
        return hasteSQL;
    }


    public static HasteSQL create(Context context)
    {
        return create(context, null);
    }

    public static HasteSQL create(Context context, Class<? extends IHasteDaoImpl> clz)
    {
        HasteSQL hasteSQL = getInstance(context);


        return hasteSQL;
    }

    @Override
    public void insert(HasteModel hasteModel)
    {

    }

    @Override
    public void update(HasteModel hasteModel)
    {

    }

    @Override
    public void insertOrReplace(HasteModel hasteModel)
    {

    }

    @Override
    public void delete(HasteModel hasteModel)
    {

    }

    @Override
    public <T extends HasteModel> T queryFirst(Class<T> clz)
    {
        return null;
    }

    @Override
    public <T> List<T> queryAll(Class<T> clz)
    {
        return null;
    }
}
