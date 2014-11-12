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
            if ((field.getModifiers() & modifierMask) != modifierMask)
            {
                String type = Type.wrap(field.getType());

                if (StringUtils.isEmpty(type))
                {
                    continue;
                }

                Property property = new Property();
                property.setType(type);
                property.setName(field.getName());
                properties.add(property);
            }
        }

        return properties.toArray(new Property[]{});
    }

    public static Object getFieldValue(String fieldName, Object obj)
    {
        Field field;
        try
        {
            field = obj.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e)
        {
            e.printStackTrace();
            return null;
        }
        field.setAccessible(true);
        try
        {
            Object value = field.get(fieldName);
            return value;
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }



}
