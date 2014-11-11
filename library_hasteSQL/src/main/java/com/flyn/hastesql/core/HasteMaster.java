package com.flyn.hastesql.core;

/**
 * Created by flyn on 2014-11-11.
 * <p/>
 * Not Thread-Safe
 */
public class HasteMaster
{


    private HasteMaster()
    {

    }

    public static HasteMaster newInstence()
    {
        return new HasteMaster();
    }



    protected  void registerDao(Class<? extends  IHasteDaoImpl> clz)
    {

    }
}
