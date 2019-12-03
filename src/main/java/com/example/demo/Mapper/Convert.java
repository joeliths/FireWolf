package com.example.demo.Mapper;

import com.sun.xml.fastinfoset.util.StringArray;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Convert {
    String[] lowAccessforbiddenFields = new String[]{"id, userId, productId","serialVersionUID"};


    public <T> T lowAccessConverter(Object originObject, Class<T> targetClass)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException
    {
        return convert(originObject,targetClass,lowAccessforbiddenFields);
    }


    private <T> T convert(Object originObject, Class<T> targetClass, String[] forbiddenFields)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException {

        forbiddenFields = new String[]{"id, userId, productId","serialVersionUID"};


        Class<?> originClass = originObject.getClass();
        Field[] originFields = originClass.getDeclaredFields();

        T targetObject = targetClass.getConstructor().newInstance();
        Field[] targetFields = targetObject.getClass().getDeclaredFields();

        for (Field originField : originFields) {
            originField.setAccessible(true);

            if (isForbiddenField(originField, forbiddenFields)){
                originField.setAccessible(false);
                continue;
            }

            int matchedFieldsIndex =indexOfMatchedField(originField,targetFields);
            if (matchedFieldsIndex==-1){
                originField.setAccessible(false);
                continue;
            }

            Field targetField = targetFields[matchedFieldsIndex];
            targetField.setAccessible(true);
            targetField.set(    targetObject,   originField.get(originObject)   );
            targetField.setAccessible(false);
            originField.setAccessible(false);
        }
        return targetObject;
    }


    private int indexOfMatchedField(Field field, Field[] fields){
        for(int i=0; i < fields.length; i++){
            if(fields[i].getName().equals(field.getName())){
                return i;
            }
        }
        return -1;
    }

    private boolean isForbiddenField(Field field, String[] forbiddenFields){
        for(String forbidden:forbiddenFields) {
            if (field.getName().equals(forbidden)) {
                return true;
            }
        }
        return false;
    }
}
