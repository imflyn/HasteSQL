package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

/**
 * Created by flyn on 2016/4/21.
 */
public class TextPrimaryEntity implements HasteModel
{
    @PrimaryKey
    private String textId;

    private String name;
    private int    age;

    public String getId()
    {
        return textId;
    }

    public void setId(String id)
    {
        this.textId = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "TextPrimaryEntity{" +
                "id='" + textId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
