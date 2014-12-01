package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

import java.util.Date;

/**
 * Created by flyn on 2014-11-13.
 */

public class People implements HasteModel
{
    @PrimaryKey(AutoIncrease = true)
    private long id;

    private String name;
    private int age;

    private Date date = new Date();

    public long getId()
    {
        return id;
    }

    public void setId(long id)
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

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    @Override
    public long getRowId()
    {
        return id;
    }

    @Override
    public void setRowId(long value)
    {
        //        LogUtils.d("PrimaryKeyValue:" + value);
        id = value;
    }

    @Override
    public String toString()
    {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
