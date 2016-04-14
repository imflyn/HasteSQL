package com.flyn.hastesql.optional;

import com.flyn.hastesql.util.ReflectUtils;

/**
 * Created by flyn on 2014-11-19.
 * Non-Thread-Safe
 */
public class ConditionExpression implements ExpressionInterface
{

    public static final String MATH_ADDITIVE                 = "+";
    public static final String MATH_SUBTRACTION              = "-";
    public static final String MATH_MULTIPLY                 = "*";
    public static final String MATH_DIVISION                 = "/";
    public static final String MATH_MODULO                   = "%";
    public static final String COMPARISON_EQUALS             = "=";
    public static final String COMPARISON_NOT_EQUALS         = "!=";
    public static final String COMPARISON_GREATER            = ">";
    public static final String COMPARISON_LESS               = "<";
    public static final String COMPARISON_GREATER_AND_EQUALS = ">=";
    public static final String COMPARISON_LESS_AND_EQUALS    = "<=";
    public static final String COMPARISON_NOT_GREATER        = "!>";
    public static final String COMPARISON_NOT_LESS           = "!<";
    public static final String LOGIC_AND                     = "AND";
    public static final String LOGIC_BETWEEN                 = "BETWEEN";
    public static final String LOGIC_EXISTS                  = "EXISTS";
    public static final String LOGIC_IN                      = "IN";
    public static final String LOGIC_NOT_IN                  = "NOT IN";
    public static final String LOGIC_LIKE                    = "LIKE";
    public static final String LOGIC_GLOB                    = "GLOB";
    public static final String LOGIC_NOT                     = "NOT";
    public static final String LOGIC_OR                      = "OR";
    public static final String LOGIC_IS_NULL                 = "IS NULL";
    public static final String LOGIC_IS                      = "IS";
    public static final String LOGIC_IS_NOT                  = "IS NOT";
    public static final String LOGIC_UNIQUE                  = "UNIQUE";
    public static final String BITWISE_AMPERSAND             = "&";
    public static final String BITWISE_OR                    = "|";
    public static final String BITWISE_COMPLEMENT            = "~";
    public static final String BITWISE_LEFT_SHIFT            = "<<";
    public static final String BITWISE_RIGHT_SHIFT           = ">>";


    protected StringBuilder stringBuilder = new StringBuilder();

    @Override
    public ConditionExpression operate(String operation, String field, Object value)
    {


        stringBuilder.append(field);
        stringBuilder.append(" ");
        stringBuilder.append(operation);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression combine(String operation)
    {
        stringBuilder.append(" ");
        stringBuilder.append(operation);
        stringBuilder.append(" ");
        return this;
    }

    @Override
    public ConditionExpression and()
    {
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_AND);
        stringBuilder.append(" ");
        return this;
    }

    @Override
    public ConditionExpression exists()
    {
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_EXISTS);
        stringBuilder.append(" ");
        return this;
    }


    @Override
    public ConditionExpression equals(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression notEquals(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_NOT_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression greater(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_GREATER);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression greaterEquals(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_GREATER_AND_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression notGreater(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_NOT_GREATER);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression less(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_LESS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression lessEquals(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_LESS_AND_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression notLess(String fieldName, Object value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(COMPARISON_NOT_LESS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : " ");
        return this;
    }

    @Override
    public ConditionExpression between(String fieldName, Object left, Object right)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_BETWEEN);
        boolean isText = ReflectUtils.isText(left);
        stringBuilder.append(isText ? " \'" : " ");
        stringBuilder.append(left);
        stringBuilder.append(isText ? "\' " : " ");
        stringBuilder.append(LOGIC_AND);
        stringBuilder.append(" ");
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(right);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    @Override
    public ConditionExpression in(String fieldName, Object... value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_IN);
        stringBuilder.append(" ( ");
        for (Object obj : value)
        {
            boolean isText = ReflectUtils.isText(obj);
            stringBuilder.append(isText ? " " : "\'");
            stringBuilder.append(obj);
            stringBuilder.append(isText ? "" : "\'");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(" ) ");
        return this;
    }

    @Override
    public ConditionExpression notIn(String fieldName, Object... value)
    {
        stringBuilder.append(fieldName);
        stringBuilder.append(LOGIC_NOT_IN);
        stringBuilder.append(" ( ");
        for (Object obj : value)
        {
            boolean isText = ReflectUtils.isText(value);
            stringBuilder.append(isText ? " " : "\'");
            stringBuilder.append(obj);
            stringBuilder.append(isText ? "" : "\'");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(" ) ");
        return this;
    }

    @Override
    public ConditionExpression or()
    {
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_OR);
        stringBuilder.append(" ");
        return this;
    }


    @Override
    public String toString()
    {
        return stringBuilder.toString();
    }

}
