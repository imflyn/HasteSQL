package com.flyn.hastesql.optional;

import com.flyn.hastesql.util.LogUtils;
import com.flyn.hastesql.util.ReflectUtils;

/**
 * Created by flyn on 2014-11-19.
 * Non-Thread-Safe
 */
public class ConditionExpression
{

    public static final String MATH_ADDITIVE = "+";
    public static final String MATH_SUBTRACTION = "-";
    public static final String MATH_MULTIPLY = "*";
    public static final String MATH_DIVISION = "/";
    public static final String MATH_MODULO = "%";
    public static final String COMPARISON_EQUALS = "=";
    public static final String COMPARISON_NOT_EQUALS = "!=";
    public static final String COMPARISON_GREATER = ">";
    public static final String COMPARISON_LESS = "<";
    public static final String COMPARISON_GREATER_AND_EQUALS = ">=";
    public static final String COMPARISON_LESS_AND_EQUALS = "<=";
    public static final String COMPARISON_NOT_GREATER = "!>";
    public static final String COMPARISON_NOT_LESS = "!<";
    public static final String LOGIC_AND = "AND";
    public static final String LOGIC_BETWEEN = "BETWEEN";
    public static final String LOGIC_EXISTS = "EXISTS";
    public static final String LOGIC_IN = "IN";
    public static final String LOGIC_NOT_IN = "NOT IN";
    public static final String LOGIC_LIKE = "LIKE";
    public static final String LOGIC_GLOB = "GLOB";
    public static final String LOGIC_NOT = "NOT";
    public static final String LOGIC_OR = "OR";
    public static final String LOGIC_IS_NULL = "IS NULL";
    public static final String LOGIC_IS = "IS";
    public static final String LOGIC_IS_NOT = "IS NOT";
    public static final String LOGIC_UNIQUE = "UNIQUE";
    public static final String BITWISE_AMPERSAND = "&";
    public static final String BITWISE_OR = "|";
    public static final String BITWISE_COMPLEMENT = "~";
    public static final String BITWISE_LEFT_SHIFT = "<<";
    public static final String BITWISE_RIGHT_SHIFT = ">>";


    protected StringBuilder stringBuilder = new StringBuilder();

    public ConditionExpression operate(String operation, Property property, Object value)
    {
        return operate(operation, property.getName(), value);
    }

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

    public ConditionExpression combine(String operation)
    {
        stringBuilder.append(" ");
        stringBuilder.append(operation);
        stringBuilder.append(" ");
        return this;
    }

    public ConditionExpression and()
    {
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_AND);
        stringBuilder.append(" ");
        return this;
    }

    public ConditionExpression exists()
    {
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_EXISTS);
        stringBuilder.append(" ");
        return this;
    }

    public ConditionExpression where()
    {
        stringBuilder.append(" ");
        stringBuilder.append("WHERE");
        stringBuilder.append(" ");
        return this;
    }

    public ConditionExpression equals(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression notEquals(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_NOT_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression greater(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_GREATER);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression greaterEquals(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_GREATER_AND_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression notGreater(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_NOT_GREATER);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression less(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_LESS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression lessEquals(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_LESS_AND_EQUALS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression notLess(Property property, Object value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_NOT_LESS);
        boolean isText = ReflectUtils.isText(value);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(value);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression between(Property property, Object left, Object right)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(COMPARISON_NOT_LESS);
        boolean isText = ReflectUtils.isText(left);
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(left);
        stringBuilder.append(isText ? "\'" : "");
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_AND);
        stringBuilder.append(" ");
        stringBuilder.append(isText ? "\'" : " ");
        stringBuilder.append(right);
        stringBuilder.append(isText ? "\'" : "");
        return this;
    }

    public ConditionExpression in(Property property, Object... value)
    {
        stringBuilder.append(property.getName());
        stringBuilder.append(LOGIC_IN);
        stringBuilder.append(" ( ");
        for (Object obj : value)
        {
            boolean isText = ReflectUtils.isText(value);
            stringBuilder.append(isText ? " " : "\'");
            stringBuilder.append(obj);
            stringBuilder.append(isText ? "" : "\'");
            stringBuilder.append(",");
        }
        stringBuilder.append(" ) ");
        return this;
    }

    public ConditionExpression notIn(Property property, Object... value)
    {
        stringBuilder.append(property.getName());
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
        stringBuilder.append(" ) ");
        return this;
    }

    public ConditionExpression or()
    {
        stringBuilder.append(" ");
        stringBuilder.append(LOGIC_OR);
        stringBuilder.append(" ");
        return this;
    }


    public String toString()
    {
        LogUtils.d(stringBuilder.toString());

        return stringBuilder.toString();
    }

}
