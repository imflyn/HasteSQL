package com.flyn.hastesql.optional;

import com.flyn.hastesql.core.HasteDao;
import com.flyn.hastesql.core.HasteModel;

import java.util.List;

/**
 * Created by flyn on 2016/4/14.
 */
public abstract class OptionProxy
{
    protected final HasteDao hasteDao;
    protected final ConditionExpression whereConditionExpression = new ConditionExpression();
    protected final ConditionExpression valueConditionExpression = new ConditionExpression();
    protected final ConditionBuilder    conditionBuilder         = new ConditionBuilder();

    public OptionProxy(HasteDao hasteDao)
    {
        this.hasteDao = hasteDao;
    }

    public OptionProxy limit(int limit)
    {
        conditionBuilder.limit(limit);
        return this;
    }

    public OptionProxy limit(int limit, int offset)
    {
        conditionBuilder.limit(limit, offset);
        return this;
    }

    public OptionProxy orderBy(String str)
    {
        conditionBuilder.orderBy(str);
        return this;
    }

    public OptionProxy orderByAsc(String str)
    {
        conditionBuilder.orderByAsc(str);
        return this;
    }

    public OptionProxy orderByDesc(String str)
    {
        conditionBuilder.orderByDesc(str);
        return this;
    }

    public OptionProxy orderBy(String... stringArray)
    {
        conditionBuilder.orderBy(stringArray);
        return this;
    }

    public OptionProxy orderByAsc(String... stringArray)
    {
        conditionBuilder.orderByAsc(stringArray);
        return this;
    }

    public OptionProxy orderByDesc(String... stringArray)
    {
        conditionBuilder.orderByDesc(stringArray);
        return this;
    }


    public OptionProxy orderBy(String[] stringArray, boolean... desc)
    {
        conditionBuilder.orderBy(stringArray, desc);
        return this;
    }

    public OptionProxy groupBy(String... stringArray)
    {
        conditionBuilder.groupBy(stringArray);
        return this;
    }

    public OptionProxy having()
    {
        conditionBuilder.having(whereConditionExpression);
        return this;
    }

    public OptionProxy where()
    {
        conditionBuilder.where(whereConditionExpression);
        return this;
    }

    public OptionProxy operate(String operation, String field, Object value)
    {
        whereConditionExpression.operate(operation, field, value);
        return this;
    }

    public OptionProxy combine(String operation)
    {
        whereConditionExpression.combine(operation);
        return this;
    }

    public OptionProxy and()
    {
        whereConditionExpression.and();
        return this;
    }

    public OptionProxy exists()
    {
        whereConditionExpression.exists();
        return this;
    }

    public OptionProxy equals(String fieldName, Object value)
    {
        whereConditionExpression.equals(fieldName, value);
        return this;
    }

    public OptionProxy notEquals(String fieldName, Object value)
    {
        whereConditionExpression.notEquals(fieldName, value);
        return this;
    }

    public OptionProxy greater(String fieldName, Object value)
    {
        whereConditionExpression.greater(fieldName, value);
        return this;
    }

    public OptionProxy greaterEquals(String fieldName, Object value)
    {
        whereConditionExpression.greaterEquals(fieldName, value);
        return this;
    }

    public OptionProxy notGreater(String fieldName, Object value)
    {
        whereConditionExpression.notGreater(fieldName, value);
        return this;
    }

    public OptionProxy less(String fieldName, Object value)
    {
        whereConditionExpression.less(fieldName, value);
        return this;
    }

    public OptionProxy lessEquals(String fieldName, Object value)
    {
        whereConditionExpression.lessEquals(fieldName, value);
        return this;
    }

    public OptionProxy notLess(String fieldName, Object value)
    {
        whereConditionExpression.notLess(fieldName, value);
        return this;
    }

    public OptionProxy between(String fieldName, Object left, Object right)
    {
        whereConditionExpression.between(fieldName, left, right);
        return this;
    }

    public OptionProxy in(String fieldName, Object... value)
    {
        whereConditionExpression.in(fieldName, value);
        return this;
    }

    public OptionProxy notIn(String fieldName, Object... value)
    {
        whereConditionExpression.notIn(fieldName, value);
        return this;
    }

    public OptionProxy or()
    {
        whereConditionExpression.or();
        return this;
    }

    public abstract void execute();

    public abstract <T extends HasteModel> List<T> get();

    public abstract <T extends HasteModel> T getFirst();
}
