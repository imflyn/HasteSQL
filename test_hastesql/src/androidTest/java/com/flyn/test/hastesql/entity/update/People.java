package com.flyn.test.hastesql.entity.update;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

import java.util.Date;

/**
 * Created by flyn on 2014-12-03.
 */
public class People implements HasteModel
{
    @PrimaryKey(AutoIncrease = true)
    private long id;

    private String name;
    private int    age;

    private Date date = new Date();

    private boolean isMan;

    private String aa;
    private String bb;

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

    public long getRowId()
    {
        return id;
    }

    public void setRowId(long value)
    {
        id = value;
    }

    public boolean isMan()
    {
        return isMan;
    }

    public void setMan(boolean isMan)
    {
        this.isMan = isMan;
    }

    public String getAa()
    {
        return aa;
    }

    public void setAa(String aa)
    {
        this.aa = aa;
    }

    public String getBb()
    {
        return bb;
    }

    public void setBb(String bb)
    {
        this.bb = bb;
    }

    @Override
    public String toString()
    {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", isMan=" + isMan +
                ", aa='" + aa + '\'' +
                ", bb='" + bb + '\'' +
                '}';
    }
}
