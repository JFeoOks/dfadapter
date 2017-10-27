package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.annotation.AccessType;
import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultParamsExtractor extends AnnotatedParamsExtractor {

    private AccessType accessType;

    public DefaultParamsExtractor(AccessType accessType) {
        this.accessType = accessType;
    }

    @Override
    public List<Field> extractFields(Class<?> beanClass) throws Exception {
        if (accessType == AccessType.METHOD) return super.extractFields(beanClass);

        Field[] declaredFields = beanClass.getDeclaredFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(ExcludeDFParam.class)) filteredFields.add(field);
        }
        return filteredFields;
    }

    @Override
    public List<Method> extractMethods(Class<?> beanClass) throws Exception {
        if (accessType == AccessType.FIELD) return super.extractMethods(beanClass);

        Method[] declaredMethods = beanClass.getDeclaredMethods();
        List<Method> filteredMethods = new ArrayList<>();

        for (Method method : declaredMethods) {
            if (isSetter(method) && !method.isAnnotationPresent(ExcludeDFParam.class)) filteredMethods.add(method);
        }

        return filteredMethods;
    }

    private static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }
}
