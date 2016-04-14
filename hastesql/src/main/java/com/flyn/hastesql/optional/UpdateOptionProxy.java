package com.flyn.hastesql.optional;

import com.flyn.hastesql.core.HasteDao;
import com.flyn.hastesql.core.HasteModel;

import java.util.List;

public class UpdateOptionProxy extends OptionProxy
{
    public UpdateOptionProxy(HasteDao hasteDao)
    {
        super(hasteDao);
    }

    @Override
    public void execute()
    {
        //TODO
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
