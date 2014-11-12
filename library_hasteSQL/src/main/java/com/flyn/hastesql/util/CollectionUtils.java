package com.flyn.hastesql.util;

import java.util.Collection;

/**
 * Created by flyn on 2014-11-11.
 */
public class CollectionUtils
{
    public static boolean isEmpty(Collection<?> collection)
    {
        return collection == null || collection.isEmpty();

    }

}
