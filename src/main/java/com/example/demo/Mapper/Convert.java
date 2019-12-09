package com.example.demo.Mapper;

import com.example.demo.entities.*;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.*;
import com.example.demo.models.pendingorder.PendingOrderModel;
import com.example.demo.models.user.UserModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public  class Convert {
    private static String[] lowAccessforbiddenFields = new String[]{ "id, userId, productId","serialVersionUID"};




    private static Map<Class, Class> EntityModelHashMap = new HashMap<Class,Class>(){
        {
            put(Customer.class, CustomerModel.class);
            put(InventoryProduct.class, InventoryProductModel.class);
            put(PendingOrder.class, PendingOrderModel.class);
            put(PendingOrderProduct.class, PendingOrderProductModel.class);
            put(Product.class, ProductModel.class);
            put(Store.class, StoreModel.class);
            put(User.class, UserModel.class);
            put(Vendor.class, VendorModel.class);
        }
    };

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
                originField.setAccessible(false);
                continue;
            }
            if( originIsNull(originField, originObject)){
                System.out.println("originfield: " + originField.getName() + "  was null");
                originField.setAccessible(false);
                continue;
            }

            int matchedFieldsIndex = indexOfMatchedField(originField,targetFields);
            if (matchedFieldsIndex==-1){
                originField.setAccessible(false);
                continue;
            }




            Field targetField = targetFields[matchedFieldsIndex];

            System.out.println("Found field: " + targetField.getName());
            System.out.println("field type: " + targetField.getType());
            targetField.setAccessible(true);



            if(isEntityToModelUUID(targetField)){
                System.out.println("target field: " +targetField.getName() + " is nested Model.");
                targetField.set(    targetObject,   originField.get(originObject).toString()   );
            }

            else if(isModelToEntityUUID(targetField)) {
                System.out.println("target field: " +targetField.getName() + " is nested Entity.");
                String originString =originField.get(originObject).toString();
                MyUUID uuidObject = (MyUUID) targetField.get(targetObject);
                MyUUID.class.getMethod("setUuid", String.class).invoke(uuidObject,originString);

            }else if(targetReferenceTypeNotNull(targetField, targetObject)){
                System.out.println("targetField was not null: " + targetField.get(targetObject));
                targetField.setAccessible(false);
                originField.setAccessible(false);
                continue;
            }

                //TODO: Here there be dragons
            else if(isNestedObject(originField)){
                System.out.println("===start of nested field: "+originField.getName()+"============================");
                Object nestedOriginObject = originField.get(originObject);

                System.out.println("Nested field: " + originField.getName());
                Class nestedTargetClass = EntityModelHashMap.get(originField.getType());
                Object nestedTarget = convert(nestedOriginObject, nestedTargetClass, forbiddenFields);
                nestedTarget = nestedTargetClass.cast(nestedTarget);
                System.out.println("===end of nested field: "+originField.getName()+"============================");
                targetField.set(targetObject, nestedTarget);

            } else if (isSet(originField)){
                Set nestedSet = (Set) originField.get(originObject);
                Set<Object> targetSet = new HashSet<>();
                for (Object nestedEntry: nestedSet) {
                    System.out.println("===start of nested field: "+originField.getName()+"============================");
                    Object nestedOriginObject = originField.get(originObject);

                    System.out.println("        nested class: "+ nestedEntry.getClass());
                    Class nestedTargetClass = EntityModelHashMap.get(nestedEntry.getClass());
                    System.out.println("                       nestedTargetClass: "+ nestedTargetClass);
                    System.out.println("nested set checkpoint 1");
                    Object nestedTarget = convert(nestedEntry, nestedTargetClass, forbiddenFields);
                    System.out.println("nested set checkpoint 2");
                    nestedTarget = nestedTargetClass.cast(nestedTarget);
                    System.out.println("nested set checkpoint 3");
                    System.out.println("===end of nested field: "+originField.getName()+"============================");
                    targetSet.add(nestedTarget);
                    System.out.println("nested set checkpoint 4");
                }
                targetField.set(targetObject, targetSet);
                System.out.println("nested set checkpoint 5");
            }

            else {
                System.out.println("in ordinary else");
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
    private static boolean isEntityToModelUUID(Field targetField){
        if(targetField.getName().equals("uuid") && targetField.getType().equals(String.class)){
            return true;
        }
        return false;
    }
    private static boolean isModelToEntityUUID(Field targetField){
        if(targetField.getName().equals("uuid") && targetField.getType().equals(MyUUID.class)){
            return true;
        }
        return false;
    }
    private static boolean isNestedObject(Field originField){
        return MyEntity.class.isAssignableFrom(originField.getType());
    }
    private static boolean originIsNull(Field field, Object originObject){
        try {
            if(field.get(originObject) == null){
                return true;
            }
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean targetReferenceTypeNotNull(Field field, Object targetObject){
        if(field.getType().isPrimitive()){
            return false;
        }
        try {
            if(field.get(targetObject) != null){
                return true;
            }
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    private static boolean isSet(Field originField){
        if (originField.getType().equals(Set.class)){
            return true;
        }
        return false;
    }


}
