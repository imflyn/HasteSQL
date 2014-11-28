package com.flyn.hastesql.core;

import com.flyn.hastesql.optional.ConditionExpression;

import java.util.List;

/**
 * Created by flyn on 2014-11-11.
 */
public interface HasteOperation
{


    void insert(HasteModel hasteModel);

    void insertAll(List<? extends HasteModel> hasteModelList);

    void update(HasteModel hasteModel);

    void updateAll(List<? extends HasteModel> hasteModelList);

    void update(Class<? extends HasteModel> clz, ConditionExpression valueExpression, ConditionExpression whereExpression);

    void insertOrReplace(HasteModel hasteModel);

    void insertOrReplaceAll(List<? extends HasteModel> hasteModelList);

    void delete(HasteModel hasteModel);

    void delete(Class<? extends HasteModel> clz, ConditionExpression conditionExpression);

    void deleteAll(Class<? extends HasteModel> clz);

    void deleteAll(List<? extends HasteModel> hasteModelList);

    <T extends HasteModel> List<T> queryAll(Class<T> clz);

    <T extends HasteModel> List<T> query(Class<T> clz, ConditionExpression conditionExpression);

    <T extends HasteModel> T queryFirst(Class<T> clz, ConditionExpression conditionExpression);

    <T extends HasteModel> T queryByKey(Class<T> clz,Object key);

    void run(String sql);

    void run(String sql, Object[] args);
}
