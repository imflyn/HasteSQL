package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

/**
 * Created by flyn on 2016/4/13.
 */
public class AutoIncreaseModel implements HasteModel
{
    @PrimaryKey(AutoIncrease = true)
    private int id;

    private String name;
    private int    age;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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
}
