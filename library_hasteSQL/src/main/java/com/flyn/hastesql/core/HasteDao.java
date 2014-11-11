package com.flyn.hastesql.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyn on 2014-11-10.
 * <p/>
 * Not Thread-Safe
 */
public class HasteDao
{
    private static final int DEFAULT_DB_VERSION = 1;
    private static final String DEFAULT_DB_NAME = "database.db";

    private List<? extends HasteTable> hasteTableList = new ArrayList<HasteTable>();


}
