package com.example.demo.Mapper;

import com.example.demo.entities.*;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.exceptions.customExceptions.ModelMapperException;
import com.example.demo.models.*;
import com.example.demo.models.pendingorder.PendingOrderModel;
import com.example.demo.models.user.UserModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public <T> T lowAccessConverter(Object originObject, Class<T> targetClass) {
        backReferences = new HashMap<Object, Object>() {    };
        try {
            return convert(originObject,targetClass,lowAccessforbiddenFields);
        } catch (Exception e) {
            throw new ModelMapperException("Could not convert between model and entity" + e);
        }
    }


    private  <T> T convert(Object originObject, Class<T> targetClass, String[] forbiddenFields)
            throws IllegalAccessException,
            NoSuchMethodException,
            InstantiationException,
            InvocationTargetException {


        Class<?> originClass = originObject.getClass();
        Method[] originMethods = originClass.getDeclaredMethods();
        System.out.println("originMethod 2:" + originMethods[2]);
        T targetObject = targetClass.getConstructor().newInstance();
        Field[] targetFields = targetObject.getClass().getDeclaredFields();

        addBackReference(originObject, targetObject);

        for (Field targetField : targetFields) {
            targetField.setAccessible(true);

            if (isForbiddenField(targetField, forbiddenFields)){
                targetField.setAccessible(false);
                continue;
            }
            /*if( originIsNull(targetField, originObject)){
                targetField.setAccessible(false);
                continue;
            }*/

            int originMethodIndex = indexOfOriginMethod(targetField,originMethods);
            if (originMethodIndex==-1){
                targetField.setAccessible(false);
                continue;
            }




            Method originMethod = originMethods[originMethodIndex];
            originMethod.setAccessible(true);


            //Probably done
            if(isEntityToModelUUID(targetField)){
                targetField.set(    targetObject,   originMethod.invoke(originObject).toString()   );
                continue;
            }
            //TODO: pass model - entity
            else if(isModelToEntityUUID(targetField)) {
                String originString = targetField.get(originObject).toString();
                MyUUID uuidObject = (MyUUID) targetField.get(targetObject);
                uuidObject.setUuid(originString);
                targetField.set(targetObject, uuidObject);
                //MyUUID.class.getMethod("setUuid", String.class).invoke(uuidObject,originString);
                targetField.setAccessible(false);
                targetField.setAccessible(false);
                continue;

            }
            else if(targetReferenceTypeNotNull(targetField, targetObject)){
                targetField.setAccessible(false);
                targetField.setAccessible(false);
                continue;
            }

                //TODO: Here there be dragons
            else if(isNestedObject(originMethod)){
                System.out.println("In usual dragons");
                Object nestedOriginObject = originMethod.invoke(originObject);
                System.out.println("targetfield type: " + targetField.getType());
                Class nestedTargetClass = targetField.getType();    //EntityModelHashMap.get(targetField.getType());

                if(isBackReference(nestedOriginObject)){
                    targetField.set(targetObject, gettargetBackReference(nestedOriginObject));
                    targetField.setAccessible(false);
                    targetField.setAccessible(false);
                    continue;
                }
                //recursion
                Object nestedTarget = convert(nestedOriginObject, nestedTargetClass, forbiddenFields);
                nestedTarget = nestedTargetClass.cast(nestedTarget);
                targetField.set(targetObject, nestedTarget);
                targetField.setAccessible(false);
                targetField.setAccessible(false);

            } else if (isSet(targetField)){
                Set nestedSet = (Set) originMethod.invoke(originObject);
                Set<Object> targetSet = new HashSet<>();
                for (Object nestedEntry: nestedSet) {
                    Object nestedOriginObject = originMethod.invoke(originObject);
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
                System.out.println("normal happy field :)");
                targetField.set(    targetObject,     originMethod.invoke(originObject)   );
            }
            targetField.setAccessible(false);
            targetField.setAccessible(false);
        }
        return targetObject;
    }


    private  int indexOfOriginMethod(Field field, Method[] methods){
        for(int i=0; i < methods.length; i++){
            String triedMethodName = methods[i].getName();
            System.out.println(triedMethodName);

            String triedPrefix = triedMethodName.substring(0, 3);

            String triedRemaining = triedMethodName.substring(3).toLowerCase();

            if(     triedPrefix.equals("get")   &&  triedRemaining.equals(field.getName().toLowerCase()) ){
                System.out.println("winner winner chicken dinner!");
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
    private  boolean isNestedObject(Method originMethod){
        System.out.println("Här ska du kolla nu pontus: " + originMethod.getReturnType());
        return MyEntity.class.isAssignableFrom(originMethod.getReturnType());
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
