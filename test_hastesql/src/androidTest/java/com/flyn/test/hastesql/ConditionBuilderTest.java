package com.flyn.test.hastesql;

import com.flyn.hastesql.optional.ConditionBuilder;
import com.flyn.hastesql.optional.ConditionExpression;
import com.flyn.hastesql.optional.Property;

import junit.framework.TestCase;

/**
 * Created by flyn on 2014-11-20.
 */
public class ConditionBuilderTest extends TestCase
{
    private Property property1 = new Property();
    private Property property2 = new Property();
    private Property property3 = new Property();

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        property1.setName("age");
        property2.setName("name");
        property3.setName("age");
    }

    public void testBuilder()
    {
        ConditionBuilder conditionBuilder = new ConditionBuilder();
        ConditionExpression whereExpression = new ConditionExpression();
        whereExpression.equals(property1, "1");
        whereExpression.and().equals(property2, "android");
        whereExpression.and().between(property3, "left", "right");
        whereExpression.or().in(property1, 1, 2, 3, 4);
        whereExpression.exists().notGreater(property1, 123);

        ConditionExpression havingExpression = new ConditionExpression();
        havingExpression.in(property2, "1", "2", "3");

        conditionBuilder.where(whereExpression).groupBy(property1, property2, property3).having(havingExpression).orderBy(property1, property2,
                property3).build();
    }
}
