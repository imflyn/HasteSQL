package com.flyn.hastesql.util;

/**
 * Created by flyn on 2014-11-11.
 */
public class StringUtils
{

    public static boolean isEmpty(String text)
    {
        return text == null || text.trim().length() == 0;
    }

}
