package com.flyn.test.hastesql;

import com.flyn.hastesql.optional.ConditionBuilder;
import com.flyn.hastesql.optional.ConditionExpression;

import junit.framework.TestCase;

/**
 * Created by flyn on 2014-11-20.
 */
public class ConditionBuilderTest extends TestCase
{

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    public void testBuilder()
    {
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        ConditionExpression whereExpression = new ConditionExpression();
        whereExpression.equals("age", "1");
        whereExpression.and().equals("name", "android");
        whereExpression.and().between("age", "left", "right");
        whereExpression.or().in("age", 1, 2, 3, 4);
        whereExpression.exists().notGreater("age", 123);

        ConditionExpression havingExpression = new ConditionExpression();
        havingExpression.in("name", "1", "2", "3");

        conditionBuilder.where(whereExpression).groupBy("age", "name", "age").having(havingExpression).orderBy("age", "name", "age").build();
    }
}
