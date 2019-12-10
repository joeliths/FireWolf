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
    private  String[] lowAccessforbiddenFields = new String[]{ "id, userId, productId","serialVersionUID"};
    Map<Object,Object> backReferences = new HashMap<Object, Object>() {    };


    private  Map<Class, Class> EntityModelHashMap = new HashMap<Class,Class>(){
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

    public <T> T lowAccessConverter(Object originObject, Class<T> targetClass)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException
    {
        backReferences = new HashMap<Object, Object>() {    };
        return convert(originObject,targetClass,lowAccessforbiddenFields);
    }


    private  <T> T convert(Object originObject, Class<T> targetClass, String[] forbiddenFields)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException {


        Class<?> originClass = originObject.getClass();
        Field[] originFields = originClass.getDeclaredFields();

        T targetObject = targetClass.getConstructor().newInstance();
        Field[] targetFields = targetObject.getClass().getDeclaredFields();

        addBackReference(originObject, targetObject);

        for (Field originField : originFields) {
            originField.setAccessible(true);

            if (isForbiddenField(originField, forbiddenFields)){
                originField.setAccessible(false);
                continue;
            }
            if( originIsNull(originField, originObject)){
                originField.setAccessible(false);
                continue;
            }

            int matchedFieldsIndex = indexOfMatchedField(originField,targetFields);
            if (matchedFieldsIndex==-1){
                originField.setAccessible(false);
                continue;
            }




            Field targetField = targetFields[matchedFieldsIndex];
            targetField.setAccessible(true);



            if(isEntityToModelUUID(targetField)){
                targetField.set(    targetObject,   originField.get(originObject).toString()   );
                continue;
            }

            else if(isModelToEntityUUID(targetField)) {
                String originString = originField.get(originObject).toString();
                MyUUID uuidObject = (MyUUID) targetField.get(targetObject);
                uuidObject.setUuid(originString);
                targetField.set(targetObject, uuidObject);
                //MyUUID.class.getMethod("setUuid", String.class).invoke(uuidObject,originString);
                targetField.setAccessible(false);
                originField.setAccessible(false);
                continue;

            }
            else if(targetReferenceTypeNotNull(targetField, targetObject)){
                targetField.setAccessible(false);
                originField.setAccessible(false);
                continue;
            }

                //TODO: Here there be dragons
            else if(isNestedObject(originField)){
                Object nestedOriginObject = originField.get(originObject);
                Class nestedTargetClass = EntityModelHashMap.get(originField.getType());

                if(isBackReference(nestedOriginObject)){
                    targetField.set(targetObject, gettargetBackReference(nestedOriginObject));
                    targetField.setAccessible(false);
                    originField.setAccessible(false);
                    continue;
                }
                //recursion
                Object nestedTarget = convert(nestedOriginObject, nestedTargetClass, forbiddenFields);
                nestedTarget = nestedTargetClass.cast(nestedTarget);
                targetField.set(targetObject, nestedTarget);
                targetField.setAccessible(false);
                originField.setAccessible(false);

            } else if (isSet(originField)){
                Set nestedSet = (Set) originField.get(originObject);
                Set<Object> targetSet = new HashSet<>();
                for (Object nestedEntry: nestedSet) {

                    Object nestedOriginObject = originField.get(originObject);
                    Class nestedTargetClass = EntityModelHashMap.get(nestedEntry.getClass());

                    if(isBackReference(nestedOriginObject)){
                        targetField.set(targetObject, gettargetBackReference(nestedOriginObject));
                        continue;
                    }

                    Object nestedTarget = convert(nestedEntry, nestedTargetClass, forbiddenFields);
                    nestedTarget = nestedTargetClass.cast(nestedTarget);
                    targetSet.add(nestedTarget);

                }
                targetField.set(targetObject, targetSet);
            }

            else {
                targetField.set(    targetObject,   originField.get(originObject)   );
            }
            targetField.setAccessible(false);
            originField.setAccessible(false);
        }
        return targetObject;
    }


    private  int indexOfMatchedField(Field field, Field[] fields){
        for(int i=0; i < fields.length; i++){
            if(fields[i].getName().equals(field.getName())){
                return i;
            }
        }
        return -1;
    }

    private  boolean isForbiddenField(Field field, String[] forbiddenFields){
        for(String forbidden:forbiddenFields) {
            if (field.getName().equals(forbidden)) {
                return true;
            }
        }
        return false;
    }
    private  boolean isEntityToModelUUID(Field targetField){
        if(targetField.getName().equals("uuid") && targetField.getType().equals(String.class)){
            return true;
        }
        return false;
    }
    private  boolean isModelToEntityUUID(Field targetField){
        if(targetField.getName().equals("uuid") && targetField.getType().equals(MyUUID.class)){
            return true;
        }
        return false;
    }
    private  boolean isNestedObject(Field originField){
        return MyEntity.class.isAssignableFrom(originField.getType());
    }
    private  boolean originIsNull(Field field, Object originObject){
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

    private  boolean targetReferenceTypeNotNull(Field field, Object targetObject){
        if(field.getType().isPrimitive()){
            return false;
        }
        try {
            System.out.println("pontus testar field.get(targetObject): " + field.get(targetObject));
            if(field.get(targetObject) != null){
                return true;
            }
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
    private  boolean isSet(Field originField){
        if (originField.getType().equals(Set.class)){
            return true;
        }
        return false;
    }

    private void addBackReference(Object oldObj, Object newObj){
        backReferences.put(oldObj, newObj);
    }

    private boolean isBackReference(Object oldObj){
        return backReferences.containsKey(oldObj);
    }

    private Object gettargetBackReference(Object oldObj){
        return backReferences.get(oldObj);
    }


}
