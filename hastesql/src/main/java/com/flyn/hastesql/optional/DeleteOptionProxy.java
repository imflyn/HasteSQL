package com.flyn.hastesql.optional;

import com.flyn.hastesql.core.HasteDao;
import com.flyn.hastesql.core.HasteModel;

import java.util.List;

public class DeleteOptionProxy extends OptionProxy
{
    public DeleteOptionProxy(HasteDao hasteDao)
    {
        super(hasteDao);
    }

    @Override
    public void execute()
    {
        //TODO
        this.hasteDao.delete(this.whereConditionExpression);
    }

    @Override
    public <T extends HasteModel> List<T> get()
    {
        return null;
    }

    @Override
    public <T extends HasteModel> T getFirst()
    {
        return null;
    }
}
