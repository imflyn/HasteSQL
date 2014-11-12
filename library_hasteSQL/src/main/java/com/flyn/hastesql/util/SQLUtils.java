package com.flyn.hastesql.util;

import android.database.sqlite.SQLiteStatement;

import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.optional.Type;

import java.util.Date;

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
                //TODO 主键，自增
                primarySql.append(" \'").append(property.getName()).append("\' ").append(property.getType()).append(" INTEGER PRIMARY KEY ");
                primarySql.append(",");
            } else
            {
                //TODO 设置UNIQUE属性
                //TODO 设置NOT NULL属性
                //TODO 设置CHECK属性
                otherSql.append(" \'").append(property.getName()).append("\' ").append(property.getType());
                otherSql.append(",");
            }
            //            db.execSQL("CREATE TABLE " + constraint + "'NOTE' (" + //
            //                    "'_id' INTEGER PRIMARY KEY ," + // 0: id
            //                    "'TEXT' TEXT NOT NULL ," + // 1: text
            //                    "'COMMENT' TEXT," + // 2: comment
            //                    "'DATE' INTEGER);"); // 3: date
        }

        sqlBuilder.append(primarySql.toString()).append(otherSql.toString());
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" )");

        return sqlBuilder.toString();
    }


    /**
     * 插入语句
     *
     * @param tableName
     * @param properties
     * @return
     */
    public static String createSQLInsert(String tableName, Property[] properties)
    {

        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("INSERT INTO ");
        sqlBuilder.append(tableName);
        sqlBuilder.append(" (");
        for (Property property : properties)
        {
            sqlBuilder.append(property.getName()).append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(") VALUES (");

        for (int i = 0; i < properties.length; i++)
        {
            sqlBuilder.append("?,");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(")");

        return sqlBuilder.toString();

    }


    public static void statementBindValue(SQLiteStatement sqLiteStatement, Property[] properties)
    {
        int index = 0;
        for (Property property : properties)
        {
            if (property.getType().equals(Type.TEXT.value()))
            {
                sqLiteStatement.bindString(index, (String) property.getValue());
            } else if (property.getType().equals(Type.DATE.value()))
            {
                sqLiteStatement.bindLong(index, ((Date) property.getValue()).getTime());
            } else if (property.getType().equals(Type.DOUBLE.value()))
            {
                sqLiteStatement.bindDouble(index, (Double) property.getValue());
            } else if (property.getType().equals(Type.INTEGER.value()))
            {
                sqLiteStatement.bindLong(index, (Integer) property.getValue());
            } else if (property.getType().equals(Type.BLOB.value()))
            {
                sqLiteStatement.bindBlob(index, (byte[]) property.getValue());
            } else if (property.getType().equals(Type.BOOLEAN.value()))
            {
                sqLiteStatement.bindString(index, String.valueOf(property.getValue()));
            } else
            {
                sqLiteStatement.bindNull(index);
            }

            index++;
        }


    }

    public static Property[] propertyBindValue(Property[] properties, Object obj)
    {
        Property[] copy_of_properties = new Property[properties.length];
        Property property;
        for (int i = 0; i < properties.length; i++)
        {
            property = properties[i];

            copy_of_properties[i].setName(property.getName());
            copy_of_properties[i].setType(property.getType());
            copy_of_properties[i].setValue(ReflectUtils.getFieldValue(property.getName(), obj));

        }

        return copy_of_properties;
    }

}
