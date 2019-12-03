package com.example.demo.Mapper;

import com.sun.xml.fastinfoset.util.StringArray;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public  class Convert {
    private static String[] lowAccessforbiddenFields = new String[]{ "id, userId, productId","serialVersionUID"};


    public static<T> T lowAccessConverter(Object originObject, Class<T> targetClass)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException
    {
        return convert(originObject,targetClass,lowAccessforbiddenFields);
    }


    private static <T> T convert(Object originObject, Class<T> targetClass, String[] forbiddenFields)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException {



        Class<?> originClass = originObject.getClass();
        Field[] originFields = originClass.getDeclaredFields();

        T targetObject = targetClass.getConstructor().newInstance();
        Field[] targetFields = targetObject.getClass().getDeclaredFields();

        for (Field originField : originFields) {
            originField.setAccessible(true);

            if (isForbiddenField(originField, forbiddenFields)){
                System.out.println("found forbidden field: " + originField.getName());
                originField.setAccessible(false);
                continue;
            }

            int matchedFieldsIndex =indexOfMatchedField(originField,targetFields);
            if (matchedFieldsIndex==-1){
                originField.setAccessible(false);
                continue;
            }



            Field targetField = targetFields[matchedFieldsIndex];

            System.out.println("Found field: " + targetField.getName());
            targetField.setAccessible(true);

            if(targetField.getName().equals("uuid")){
                targetField.set(    targetObject,   originField.get(originObject).toString()   );
            }else{
                targetField.set(    targetObject,   originField.get(originObject)   );
            }

            targetField.setAccessible(false);
            originField.setAccessible(false);
        }
        return targetObject;
    }


    private static int indexOfMatchedField(Field field, Field[] fields){
        for(int i=0; i < fields.length; i++){
            if(fields[i].getName().equals(field.getName())){
                return i;
            }
        }
        return -1;
    }

    private static boolean isForbiddenField(Field field, String[] forbiddenFields){
        for(String forbidden:forbiddenFields) {
            if (field.getName().equals(forbidden)) {
                return true;
            }
        }
        return false;
    }
}
