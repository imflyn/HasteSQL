package com.flyn.hastesql.util;

import android.text.TextUtils;

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
        Property property;
        for (Field field : fields)
        {
            if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()))
            {
                String type = Type.wrap(field.getType());

                if (TextUtils.isEmpty(type))
                {
                    continue;
                }

                property = new Property();
                property.setType(type);
                property.setName(field.getName());
                property.setField(field);
                properties.add(property);
            }
        }

        return properties.toArray(new Property[properties.size()]);
    }

    public static Object getFieldValue(Field field, Object obj)
    {
        field.setAccessible(true);
        Class<?> type = field.getType();
        try
        {
            if (type.equals(boolean.class))
            {
                return field.getBoolean(obj);
            } else if (type.equals(byte.class))
            {
                return field.getByte(obj);
            } else if (type.equals(double.class))
            {
                return field.getDouble(obj);
            } else if (type.equals(char.class))
            {
                return field.getChar(obj);
            } else if (type.equals(float.class))
            {
                return field.getFloat(obj);
            } else if (type.equals(int.class))
            {
                return field.getInt(obj);
            } else if (type.equals(long.class))
            {
                return field.getLong(obj);
            } else if (type.equals(short.class))
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
            objects[i] = getFieldValue(properties[i].getField(), obj);
        }
        return objects;
    }

}
