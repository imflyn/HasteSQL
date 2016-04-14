package com.flyn.hastesql.optional;

/**
 * Created by flyn on 2016/4/14.
 */
public interface ExpressionInterface
{
    ConditionExpression operate(String operation, String field, Object value);

    ConditionExpression combine(String operation);

    ConditionExpression and();

    ConditionExpression exists();

    ConditionExpression equals(String fieldName, Object value);

    ConditionExpression notEquals(String fieldName, Object value);

    ConditionExpression greater(String fieldName, Object value);

    ConditionExpression greaterEquals(String fieldName, Object value);

    ConditionExpression notGreater(String fieldName, Object value);

    ConditionExpression less(String fieldName, Object value);

    ConditionExpression lessEquals(String fieldName, Object value);

    ConditionExpression notLess(String fieldName, Object value);

    ConditionExpression between(String fieldName, Object left, Object right);

    ConditionExpression in(String fieldName, Object... value);

    ConditionExpression notIn(String fieldName, Object... value);

    ConditionExpression or();
}
