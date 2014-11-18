package com.flyn.hastesql.optional;

/**
 * Created by flyn on 2014-11-18.
 * Non-Thread-Safe
 */
public class WhereCondition
{

    private StringBuilder whereBuilder = new StringBuilder();


    private void equal(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" = ");
        whereBuilder.append(value);
    }

    private void like(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" = ");
        whereBuilder.append(value);
    }

    private void notEqual(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" <> ");
        whereBuilder.append(value);

    }

    private void between(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" <> ");
        whereBuilder.append(value);
    }

    private void greater(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" > ");
        whereBuilder.append(value);
    }

    private void greaterOrEqual(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" >= ");
        whereBuilder.append(value);
    }

    private void less(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" < ");
        whereBuilder.append(value);
    }

    private void lessOrEqual(Property property, Object value)
    {
        whereBuilder.append(property.getName());
        whereBuilder.append(" <= ");
        whereBuilder.append(value);
    }
}