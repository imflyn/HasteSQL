package com.flyn.hastesql.optional;

import com.flyn.hastesql.core.HasteDao;
import com.flyn.hastesql.core.HasteModel;

import java.util.List;

public class QueryOptionProxy extends OptionProxy
{
    public QueryOptionProxy(HasteDao hasteDao)
    {
        super(hasteDao);
    }

    @Override
    public void execute()
    {

    }

    @Override
    public <T extends HasteModel> List<T> get()
    {//TODO
        return this.hasteDao.query(this.conditionBuilder);
    }

    @Override
    public <T extends HasteModel> T getFirst()
    {
        return this.hasteDao.queryFirst(this.conditionBuilder);
    }
}
