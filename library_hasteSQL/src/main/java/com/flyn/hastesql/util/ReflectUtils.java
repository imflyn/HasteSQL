package com.flyn.hastesql.util;

import com.flyn.hastesql.annotation.Constraint;
import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.optional.Property;
import com.flyn.hastesql.optional.Type;

import java.lang.annotation.Annotation;
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

                if (StringUtils.isEmpty(type))
                {
                    continue;
                }

                property = new Property();
                property.setType(type);
                property.setName(field.getName());
                property.setField(field);

                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations)
                {
                    if (annotation.annotationType().equals(PrimaryKey.class))
                    {
                        PrimaryKey primaryKey = (PrimaryKey) annotation;
                        property.setAutoIncrease(primaryKey.AutoIncrease());
                        property.setPrimaryKey(true);
                    }
                    if (annotation.annotationType().equals(Constraint.class))
                    {
                        Constraint constraint = (Constraint) annotation;
                        property.setNotNull(constraint.notNull());
                        property.setCheck(constraint.check());
                        property.setUnique(constraint.unique());
                    }
                }
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

    public static Object[] getFieldValueArray(Property[] properties, Object obj, boolean skipAutoIncrease)
    {
        ArrayList<Object> objectArrayList = new ArrayList<Object>(properties.length);
        for (int i = 0; i < properties.length; i++)
        {
            if (properties[i].isAutoIncrease() && skipAutoIncrease)
            {
                continue;
            }
            objectArrayList.add(getFieldValue(properties[i].getField(), obj));
        }
        return objectArrayList.toArray();
    }

    public static void setFieldValue(Field field, Object object, Object value)
    {
        if (value == null)
            return;

        field.setAccessible(true);
        Class<?> type = field.getType();
        try
        {
            if (type.equals(boolean.class))
            {
                field.setBoolean(object, (Boolean) value);
            } else if (type.equals(byte.class))
            {
                field.setByte(object, (Byte) value);
            } else if (type.equals(double.class))
            {
                field.setDouble(object, (Double) value);
            } else if (type.equals(char.class))
            {
                field.setChar(object, (Character) value);
            } else if (type.equals(float.class))
            {
                field.setFloat(object, (Float) value);
            } else if (type.equals(int.class))
            {
                field.setInt(object, (Integer) value);
            } else if (type.equals(long.class))
            {
                field.setLong(object, (Long) value);
            } else if (type.equals(short.class))
            {
                field.setShort(object, (Short) value);
            } else
            {
                field.set(object, value);
            }
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean isText(Object obj)
    {
        return obj instanceof String;
    }

}
