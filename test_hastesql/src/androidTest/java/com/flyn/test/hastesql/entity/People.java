package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.core.HasteModel;

import java.util.Date;

/**
 * Created by flyn on 2014-11-13.
 */

public class People implements HasteModel
{
    @PrimaryKey(isAutoIncrease = true)
    private int id1 = 1;

    private String name = "123";

    private int age = 19;

    private Date date = new Date();
}
