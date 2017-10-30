package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.annotation.AccessType;
import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
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

        return extractActualFields();
    }

    private List<Field> extractActualFields() throws Exception {
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

        List<Method> filteredMethods = new ArrayList<>();

        for (Field field : extractActualFields()) {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(beanClass, field.getName());
            if (propertyDescriptor == null) continue;

            Method readMethod = propertyDescriptor.getReadMethod();
            if (readMethod != null && !readMethod.isAnnotationPresent(ExcludeDFParam.class))
                filteredMethods.add(readMethod);
        }

        return filteredMethods;
    }

    @Override
    public List<Method> extractSetMethods() throws Exception {
        if (accessType == AccessType.FIELD) return super.extractSetMethods();

        List<Method> filteredMethods = new ArrayList<>();

        for (Field field : extractActualFields()) {
            PropertyDescriptor propertyDescriptor = BeanUtils.getPropertyDescriptor(beanClass, field.getName());
            if (propertyDescriptor == null) continue;

            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null && !writeMethod.isAnnotationPresent(ExcludeDFParam.class))
                filteredMethods.add(writeMethod);
        }

        return filteredMethods;
    }
}
