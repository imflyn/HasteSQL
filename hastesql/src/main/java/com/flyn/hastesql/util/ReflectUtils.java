package com.flyn.hastesql.util;

import com.flyn.hastesql.annotation.Constraint;
import com.flyn.hastesql.annotation.ForeignKey;
import com.flyn.hastesql.annotation.PrimaryKey;
import com.flyn.hastesql.converter.AbstractConverter;
import com.flyn.hastesql.converter.ConverterFactory;
import com.flyn.hastesql.converter.LongConverter;
import com.flyn.hastesql.converter.LongObjectConverter;
import com.flyn.hastesql.core.HasteModel;
import com.flyn.hastesql.core.HasteTable;
import com.flyn.hastesql.optional.Property;

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

        ArrayList<Property> properties = new ArrayList<>();
        Property property;
        for (Field field : fields)
        {

            if (!Modifier.isFinal(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()))
            {
                AbstractConverter abstractConverter = ConverterFactory.getConverter(field);
                if (abstractConverter == null)
                {
                    continue;
                }

                property = new Property();
                property.setName(field.getName());
                property.setConverter(abstractConverter);

                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations)
                {
                    if (annotation.annotationType().equals(PrimaryKey.class))
                    {
                        PrimaryKey primaryKey = (PrimaryKey) annotation;
                        property.setAutoIncrease(primaryKey.AutoIncrease());
                        property.setPrimaryKey(true);
                    } else if (annotation.annotationType().equals(ForeignKey.class))
                    {
                        //TODO Add support foreign key


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

    public static Object[] getFieldValueArray(Property[] properties, Object obj, boolean skipAutoIncrease) throws IllegalAccessException
    {
        ArrayList<Object> objectArrayList = new ArrayList<>(properties.length);
        for (int i = 0; i < properties.length; i++)
        {
            if (properties[i].isAutoIncrease() && skipAutoIncrease)
            {
                continue;
            }
            objectArrayList.add(properties[i].getConverter().getValue(obj));
        }
        return objectArrayList.toArray();
    }

    public static Object getFieldValue(Property property, Object obj) throws IllegalAccessException
    {
        return property.getConverter().getValue(obj);
    }


    public static boolean isText(Object obj)
    {
        return obj instanceof String;
    }

    public static boolean checkPropertiesValidity(Property[] properties)
    {
        for (Property property : properties)
        {
            if (property.isPrimaryKey() && property.isAutoIncrease())
            {
                if (!(property.getConverter() instanceof LongConverter) && !(property.getConverter() instanceof LongObjectConverter))
                    throw new IllegalStateException("AutoIncrease field data type is not long or Long.");

                break;
            }
        }
        return true;
    }

    public static long getRowIdValue(HasteTable hasteTable, HasteModel hasteModel)
    {
        if (hasteTable.hasPrimaryKey() && hasteTable.isAutoIncrease())
        {
            try
            {
                return Long.valueOf(hasteTable.getPrimaryKey().getConverter().getValue(hasteModel).toString());
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            } catch (ClassCastException e1)
            {
                e1.printStackTrace();
            }
        }
        return -1;
    }

}
