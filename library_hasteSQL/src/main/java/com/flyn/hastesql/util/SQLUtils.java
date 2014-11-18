package com.flyn.hastesql.util;

import com.flyn.hastesql.optional.Property;

/**
 * Created by flyn on 2014-11-12.
 */
public class SQLUtils
{

    /**
     * 检查是否存在该表
     *
     * @param tableName
     * @return
     */
    public static String createSQLCheckTableExits(String tableName)
    {
        String sql = "SELECT COUNT(*) AS c FROM sqlite_master WHERE type='table' AND name='" + tableName + "'";
        return sql;
    }

    /**
     * 建表
     *
     * @param tableName
     * @param properties
     * @return
     */
    public static String createSQLCreateTable(String tableName, Property[] properties)
    {
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS ");
        sqlBuilder.append(tableName);
        sqlBuilder.append(" ( ");

        StringBuilder primarySql = new StringBuilder();
        StringBuilder otherSql = new StringBuilder();

        for (Property property : properties)
        {
            if (property.getName().equalsIgnoreCase("id") || property.getName().equalsIgnoreCase("_id"))
            {
                // 主键，自增
                primarySql.append(" \'").append(property.getName()).append("\' ").append(" INTEGER PRIMARY KEY");
                if (property.isAutoIncrease())
                {
                    primarySql.append(" AUTOINCREMENT ");
                }
                primarySql.append(",");
            } else
            {
                otherSql.append(" \'").append(property.getName()).append("\' ").append(property.getType());
                if (property.isPrimaryKey())
                {
                    otherSql.append(" PRIMARY KEY ");
                    if (property.isAutoIncrease())
                    {
                        otherSql.append(" AUTOINCREMENT ");
                    }
                }
                // 设置UNIQUE属性
                // 设置NOT NULL属性
                // 设置CHECK属性
                if (!StringUtils.isEmpty(property.getCheck()))
                {
                    otherSql.append(" CHECK(");
                    otherSql.append(property.getCheck());
                    otherSql.append(")");
                }
                if (property.isNotNull())
                {
                    otherSql.append(" NOT NULL ");
                }
                if (property.isUnique())
                {
                    otherSql.append(" UNIQUE ");
                }
                otherSql.append(",");
            }
        }

        sqlBuilder.append(primarySql.toString()).append(otherSql.toString());
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

    public static String createSQLDeleteAll(String tableName)
    {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" DELETE FROM  ");
        sqlBuilder.append(tableName);

        return sqlBuilder.toString();
    }


}
