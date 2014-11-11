package com.flyn.hastesql.core;

import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 */
public interface HasteOperation
{


    void insert(HasteModel hasteModel);

    void insert(HasteModel hasteModel, String prefix, String suffix);

    void update(HasteModel hasteModel);

    void update(HasteModel hasteModel, String prefix, String suffix);

    void insertOrReplace(HasteModel hasteModel);

    void insertOrReplace(HasteModel hasteModel, String prefix, String suffix);

    void delete(HasteModel hasteModel);

    void delete(HasteModel hasteModel, String prefix, String suffix);

    <T extends HasteModel> T queryFirst(Class<T> clz);

    <T extends HasteModel> T queryFirst(Class<T> clz, String prefix, String suffix);

    <T extends HasteModel> List<T> queryAll(Class<T> clz);

    <T extends HasteModel> List<T> queryAll(Class<T> clz, String prefix, String suffix);


}
