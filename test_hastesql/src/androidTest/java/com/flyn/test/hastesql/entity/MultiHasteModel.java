package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

import java.util.Arrays;
import java.util.List;

public class MultiHasteModel implements HasteModel
{

    @PrimaryKey(AutoIncrease = true)
    private long       id;
    private String     name;
    private People[]   peopleArray;
    private List<Name> nameList;

    @Override
    public String toString()
    {
        return "MultiHasteModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", peopleArray=" + Arrays.toString(peopleArray) +
                ", nameList=" + nameList +
                '}';
    }

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

    public People[] getPeopleArray()
    {
        return peopleArray;
    }

    public void setPeopleArray(People[] peopleArray)
    {
        this.peopleArray = peopleArray;
    }

    public List<Name> getNameList()
    {
        return nameList;
    }

    public void setNameList(List<Name> nameList)
    {
        this.nameList = nameList;
    }
}
