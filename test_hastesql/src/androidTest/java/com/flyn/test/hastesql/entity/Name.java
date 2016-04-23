package com.flyn.test.hastesql.entity;

import com.flyn.hastesql.core.HasteModel;

/**
 * Created by flyn on 2016/4/23.
 */
public class Name implements HasteModel
{

    private String message;
    private String locale;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getLocale()
    {
        return locale;
    }

    public void setLocale(String locale)
    {
        this.locale = locale;
    }
}
