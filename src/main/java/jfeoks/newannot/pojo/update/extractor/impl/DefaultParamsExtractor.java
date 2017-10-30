package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.annotation.AccessType;
import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DefaultParamsExtractor extends AnnotatedParamsExtractor {

    private Class<?> beanClass;
    private AccessType accessType;

    protected DefaultParamsExtractor(Class<?> beanClass, AccessType accessType) {
        super(beanClass);
        this.beanClass = beanClass;
        this.accessType = accessType;
    }

    @Override
    public List<Field> extractFields() throws Exception {
        if (accessType == AccessType.METHOD) return super.extractFields();

        Field[] declaredFields = beanClass.getDeclaredFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(ExcludeDFParam.class)) filteredFields.add(field);
        }
        return filteredFields;
    }

    @Override
    public List<Method> extractGetMethods() throws Exception {
        if (accessType == AccessType.FIELD) return super.extractGetMethods();

        Method[] declaredMethods = beanClass.getDeclaredMethods();
        List<Method> filteredMethods = new ArrayList<>();

        for (Method method : declaredMethods) {
            if (isGetter(method) && !method.isAnnotationPresent(ExcludeDFParam.class)) filteredMethods.add(method);
        }

        return filteredMethods;
    }

    @Override
    public List<Method> extractSetMethods() throws Exception {
        if (accessType == AccessType.FIELD) return super.extractSetMethods();

        Method[] declaredMethods = beanClass.getDeclaredMethods();
        List<Method> filteredMethods = new ArrayList<>();

        for (Method method : declaredMethods) {
            if (isSetter(method) && !method.isAnnotationPresent(ExcludeDFParam.class)) filteredMethods.add(method);
        }

        return filteredMethods;
    }
}
