package com.flyn.hastesql.optional;

import com.flyn.hastesql.util.LogUtils;

/**
 * Created by flyn on 2014-11-19.
 * Non-Thread-Safe
 */
public class ConditionBuilder
{
    private StringBuilder stringBuilder = new StringBuilder();


    public ConditionBuilder where(ConditionExpression conditionExpression)
    {

        stringBuilder.append(" WHERE ");
        stringBuilder.append(conditionExpression.toString());
        stringBuilder.append(" ");
        return this;
    }

    public ConditionBuilder limit(int limit)
    {
        stringBuilder.append(" LIMIT ");
        stringBuilder.append(limit);
        stringBuilder.append(" ");
        return this;
    }

    public ConditionBuilder limit(int limit, int offset)
    {
        stringBuilder.append(" LIMIT ");
        stringBuilder.append(limit);
        stringBuilder.append(" OFFSET ");
        stringBuilder.append(offset);
        stringBuilder.append(" ");
        return this;
    }

    public ConditionBuilder orderBy(Property property)
    {

        stringBuilder.append(" ORDER BY ");
        stringBuilder.append(property.getName());
        stringBuilder.append(" ");
        return this;
    }

    public ConditionBuilder orderByAsc(Property property)
    {

        stringBuilder.append(" ORDER BY ");
        stringBuilder.append(property.getName());
        stringBuilder.append(" ASC ");
        return this;
    }

    public ConditionBuilder orderByDesc(Property property)
    {

        stringBuilder.append(" ORDER BY ");
        stringBuilder.append(property.getName());
        stringBuilder.append(" DESC ");
        return this;
    }

    public ConditionBuilder orderBy(Property... properties)
    {

        stringBuilder.append(" ORDER BY ");
        for (Property property : properties)
        {
            stringBuilder.append(property.getName());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }

    public ConditionBuilder orderByAsc(Property... properties)
    {

        stringBuilder.append(" ORDER BY ");
        for (Property property : properties)
        {
            stringBuilder.append(property.getName());
            stringBuilder.append(" ASC ");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return this;
    }

    public ConditionBuilder orderByDesc(Property... properties)
    {
        stringBuilder.append(" ORDER BY ");
        for (Property property : properties)
        {
            stringBuilder.append(property.getName());
            stringBuilder.append(" DESC ");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }


    public ConditionBuilder orderBy(Property[] properties, boolean... desc)
    {
        stringBuilder.append(" ORDER BY ");
        for (int i = 0; i < properties.length; i++)
        {
            stringBuilder.append(properties[i].getName());
            if (desc[i])
            {
                stringBuilder.append(" DESC ");
            }
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }

    public ConditionBuilder groupBy(Property... properties)
    {
        stringBuilder.append(" GROUP BY ");
        for (Property property : properties)
        {
            stringBuilder.append(property.getName());
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }

    public ConditionBuilder having(ConditionExpression conditionExpression)
    {
        stringBuilder.append(" having ");
        stringBuilder.append(conditionExpression.toString());
        stringBuilder.append(" ");
        return this;
    }

    public String build()
    {
        LogUtils.d("[" + stringBuilder.toString() + "]");
        return stringBuilder.toString();
    }
}
