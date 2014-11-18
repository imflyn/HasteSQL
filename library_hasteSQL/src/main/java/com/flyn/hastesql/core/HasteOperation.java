package com.flyn.hastesql.core;

import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 */
public interface HasteOperation
{


    void insert(HasteModel hasteModel);

    void insertAll(List<? extends HasteModel> hasteModelList);

    void update(HasteModel hasteModel);

    void insertOrReplace(HasteModel hasteModel);

    void insertOrReplaceAll(List<? extends HasteModel> hasteModelList);

    void delete(HasteModel hasteModel);

    void deleteAll(Class<? extends HasteModel> clz);

    void deleteAll(List<? extends HasteModel> hasteModelList);


    <T extends HasteModel> T queryFirst(Class<T> clz);


    <T extends HasteModel> List<T> queryAll(Class<T> clz);


}
