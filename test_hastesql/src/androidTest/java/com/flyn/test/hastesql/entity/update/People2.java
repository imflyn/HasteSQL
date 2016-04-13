package com.flyn.test.hastesql.entity.update;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

import java.util.Date;

/**
 * Created by flyn on 2014-12-03.
 */
public class People2 implements HasteModel
{
    @PrimaryKey
    private String uid;
    private String name;
    private int    age;

    private Date date = new Date();


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

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }


    @Override
    public String toString()
    {
        return "People2{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
