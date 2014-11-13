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
            if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()))
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

        return properties.toArray(new Property[properties.size()]);
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
            if (field.getType().equals(boolean.class))
            {
                return field.getBoolean(obj);
            } else if (field.getType().equals(byte.class))
            {
                return field.getByte(obj);
            } else if (field.getType().equals(double.class))
            {
                return field.getDouble(obj);
            } else if (field.getType().equals(char.class))
            {
                return field.getChar(obj);
            } else if (field.getType().equals(float.class))
            {
                return field.getFloat(obj);
            } else if (field.getType().equals(int.class))
            {
                return field.getInt(obj);
            } else if (field.getType().equals(long.class))
            {
                return field.getLong(obj);
            } else if (field.getType().equals(short.class))
            {
                return field.getShort(obj);
            } else
            {
                return field.get(obj);
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Object[] getFieldValueArray(Property[] properties, Object obj)
    {
        Object[] objects = new Object[properties.length];
        for (int i = 0; i < properties.length; i++)
        {
            objects[i] = getFieldValue(properties[i].getName(), obj);
        }
        return objects;
    }

}
