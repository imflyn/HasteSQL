package com.flyn.hastesql.core;

import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 */
public interface HasteOperation
{


    void insert(HasteModel hasteModel);

    void update(HasteModel hasteModel);

    void insertOrReplace(HasteModel hasteModel);

    void delete(HasteModel hasteModel);

    <T extends HasteModel> T queryFirst(Class<T> clz);

    <T> List<T> queryAll(Class<T> clz);



}
