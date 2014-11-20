package com.flyn.hastesql.util;

import com.flyn.hastesql.optional.ConditionBuilder;
import com.flyn.hastesql.optional.ConditionExpression;
import com.flyn.hastesql.optional.Property;

/**
 * Created by flyn on 2014-11-12.
 */
public class SQLUtils
{

    public static String createSQLCheckTableExits(String tableName)
    {
        String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + tableName + "'";
        return sql;
    }

    public static String createSQLGetMaxId(String tableName)
    {
        String sql = "SELECT SEQ  FROM sqlite_sequence WHERE  name='" + tableName + "'";
        return sql;
    }

    public static String createSQLCreateTable(String tableName, Property[] properties)
    {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS ");
        sqlBuilder.append(tableName);
        sqlBuilder.append(" ( ");

        for (Property property : properties)
        {
            sqlBuilder.append(" \'").append(property.getName()).append("\' ").append(property.getType());
            if (property.isPrimaryKey())
            {
                sqlBuilder.append(" PRIMARY KEY ");
                if (property.isAutoIncrease())
                {
                    sqlBuilder.append(" AUTOINCREMENT ");
                }
            }
            // 设置UNIQUE属性
            // 设置NOT NULL属性
            // 设置CHECK属性
            if (!StringUtils.isEmpty(property.getCheck()))
            {
                sqlBuilder.append(" CHECK(");
                sqlBuilder.append(property.getCheck());
                sqlBuilder.append(")");
            }
            if (property.isNotNull())
            {
                sqlBuilder.append(" NOT NULL ");
            }
            if (property.isUnique())
            {
                sqlBuilder.append(" UNIQUE ");
            }
            sqlBuilder.append(",");
        }

        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" )");

        return sqlBuilder.toString();
    }


    public static String createSQLInsert(String tableName, Property[] properties)
    {

        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        sqlBuilder.append("INSERT INTO ");
        sqlBuilder.append(tableName);
        sqlBuilder.append(" (");
        for (Property property : properties)
        {
            if (property.isAutoIncrease())
            {
                continue;
            }
            sqlBuilder.append(property.getName()).append(",");
            valueBuilder.append("?,");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(") VALUES (");

        valueBuilder.deleteCharAt(valueBuilder.length() - 1);
        valueBuilder.append(")");

        sqlBuilder.append(valueBuilder.toString());

        return sqlBuilder.toString();

    }

    public static String createSQLInsertOrReplace(String tableName, Property[] properties)
    {
        StringBuilder sqlBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        sqlBuilder.append("INSERT OR REPLACE INTO ");
        sqlBuilder.append(tableName);
        sqlBuilder.append(" (");
        for (Property property : properties)
        {
            sqlBuilder.append(property.getName()).append(",");
            valueBuilder.append("?,");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(") VALUES (");

        valueBuilder.deleteCharAt(valueBuilder.length() - 1);
        valueBuilder.append(")");

        sqlBuilder.append(valueBuilder.toString());

        return sqlBuilder.toString();

    }

    public static String createSQLDelete(String tableName)
    {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" DELETE FROM  ");
        sqlBuilder.append(tableName);

        return sqlBuilder.toString();
    }

    public static String createSQLDeleteByKey(String tableName, Property property)
    {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" DELETE FROM  ");
        sqlBuilder.append(tableName);

        ConditionExpression conditionExpression = new ConditionExpression();
        conditionExpression.equals(property.getName(), '?');
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        conditionBuilder.where(conditionExpression);
        sqlBuilder.append(conditionBuilder.build());

        return sqlBuilder.toString();
    }

    public static String createSQLDelete(String tableName, Property[] properties)
    {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" DELETE FROM  ");
        sqlBuilder.append(tableName);

        ConditionExpression conditionExpression = new ConditionExpression();
        for (int i = 0; i < properties.length; i++)
        {
            if (i != 0)
            {
                conditionExpression.and();
            }
            conditionExpression.equals(properties[i].getName(), '?');

        }
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        conditionBuilder.where(conditionExpression);
        sqlBuilder.append(conditionBuilder.build());

        return sqlBuilder.toString();
    }

}
