package com.flyn.hastesql.optional;

/**
 * Created by flyn on 2014-11-19.
 * Non-Thread-Safe
 */
public class OperatorBuilder
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


    private StringBuilder stringBuilder = new StringBuilder();

    public OperatorBuilder operate(String operation, Property property)
    {
        return operate(operation, property.getName(), property.getValue());
    }

    public OperatorBuilder operate(String operation, Property property, Object value)
    {
        return operate(operation, property.getName(), value);
    }

    public OperatorBuilder operate(String operation, String field, Object value)
    {
        stringBuilder.append(field);
        stringBuilder.append(" ");
        stringBuilder.append(operation);
        stringBuilder.append(" ");
        stringBuilder.append(value);

        return this;
    }


    public String toString()
    {
        return stringBuilder.toString();
    }
}
