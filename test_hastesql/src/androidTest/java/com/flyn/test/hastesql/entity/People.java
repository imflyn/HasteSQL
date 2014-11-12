package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.core.HasteModel;

/**
 * Created by flyn on 2014-11-12.
 */
public class People implements HasteModel
{

    private String name;
    private int age;

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
}
