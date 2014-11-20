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

    public ConditionBuilder orderBy(String str)
    {

        stringBuilder.append(" ORDER BY ");
        stringBuilder.append(str);
        stringBuilder.append(" ");
        return this;
    }

    public ConditionBuilder orderByAsc(String str)
    {

        stringBuilder.append(" ORDER BY ");
        stringBuilder.append(str);
        stringBuilder.append(" ASC ");
        return this;
    }

    public ConditionBuilder orderByDesc(String str)
    {

        stringBuilder.append(" ORDER BY ");
        stringBuilder.append(str);
        stringBuilder.append(" DESC ");
        return this;
    }

    public ConditionBuilder orderBy(String... stringArray)
    {

        stringBuilder.append(" ORDER BY ");
        for (String str : stringArray)
        {
            stringBuilder.append(str);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }

    public ConditionBuilder orderByAsc(String... stringArray)
    {

        stringBuilder.append(" ORDER BY ");
        for (String str : stringArray)
        {
            stringBuilder.append(str);
            stringBuilder.append(" ASC ");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return this;
    }

    public ConditionBuilder orderByDesc(String... stringArray)
    {
        stringBuilder.append(" ORDER BY ");
        for (String str : stringArray)
        {
            stringBuilder.append(str);
            stringBuilder.append(" DESC ");
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }


    public ConditionBuilder orderBy(String[] stringArray, boolean... desc)
    {
        stringBuilder.append(" ORDER BY ");
        for (int i = 0; i < stringArray.length; i++)
        {
            stringBuilder.append(stringArray[i]);
            if (desc[i])
            {
                stringBuilder.append(" DESC ");
            }
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return this;
    }

    public ConditionBuilder groupBy(String... stringArray)
    {
        stringBuilder.append(" GROUP BY ");
        for (String str : stringArray)
        {
            stringBuilder.append(str);
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
