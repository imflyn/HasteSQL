package com.flyn.hastesql.core;

/**
 * Created by flyn on 2014-11-11.
 */
public interface IHasteDaoImpl
{

    public <T extends HasteModel> Class<T> getHasteModelClz();

}
