package com.flyn.hastesql.util;

import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.optional.Type;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Created by flyn on 2014-11-11.
 */
public class ReflectUtils
{

    public static Property[] getPropertyArray(Class<?> clz)
    {
        Field[] fields = clz.getDeclaredFields();

        if (null == fields || fields.length == 0)
        {
            return new Property[0];
        }

        ArrayList<Property> properties = new ArrayList<Property>();
        final int modifierMask = Modifier.STATIC | Modifier.TRANSIENT | Modifier.FINAL;
        for (Field field : fields)
        {
            if ((field.getModifiers() & modifierMask) != modifierMask && (!(field.getClass().equals(Object.class))))
            {
                Property property = new Property();

                property.setName(field.getName());
                property.setType(Type.wrap(field.getType()));
                properties.add(property);
            }
        }

        return properties.toArray(new Property[]{});
    }

}
