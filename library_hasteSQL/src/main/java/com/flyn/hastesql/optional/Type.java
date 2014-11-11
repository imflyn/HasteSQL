package com.flyn.hastesql.optional;

/**
 * Created by flyn on 2014-11-11.
 */
public enum Type
{
    BOOLEAN("BOOLEAN"), TEXT("TEXT"), DOUBLE("DOUBLE"), INTEGER("INTEGER"), DATE("DATE"), BLOB("BLOB"), NULL("NULL");

    private String type;

    Type(String type)
    {
        this.type = type;
    }


    String value()
    {
        return type;
    }

}
