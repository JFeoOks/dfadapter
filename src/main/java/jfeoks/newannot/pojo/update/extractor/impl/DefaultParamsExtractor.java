package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.update.annotation.AccessType;
import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultParamsExtractor extends AnnotatedParamsExtractor {

    private Class<?> beanClass;
    private AccessType accessType;
    private List<Field> actualFields;
    private Map<String, PropertyDescriptor> propertyDescriptorMap = new HashMap<>();

    protected DefaultParamsExtractor(Class<?> beanClass, AccessType accessType) {
        super(beanClass);
        this.beanClass = beanClass;
        this.accessType = accessType;
    }

    @Override
    public List<Field> extractFields() throws Exception {
        if (accessType == AccessType.METHOD) return super.extractFields();

        List<Field> fields = extractActualFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : fields) {
            PropertyDescriptor propertyDescriptor = getFieldPropertyDescriptor(field.getName());

            if (propertyDescriptor == null) {
                filteredFields.add(field);
                continue;
            }

            Method readMethod = propertyDescriptor.getReadMethod();

            boolean isReadMethodExists = readMethod != null;
            if (isReadMethodExists && readMethod.isAnnotationPresent(DFParam.class)) continue;

            filteredFields.add(field);
        }

        return filteredFields;
    }

    private List<Field> extractActualFields() throws Exception {
        if (this.actualFields != null) return this.actualFields;

        Field[] declaredFields = beanClass.getDeclaredFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(ExcludeDFParam.class)) filteredFields.add(field);
        }

        this.actualFields = filteredFields;
        return filteredFields;
    }

    @Override
    public List<Method> extractGetMethods() throws Exception {
        if (accessType == AccessType.FIELD) {
            List<Method> methods = super.extractGetMethods();
            List<Method> filteredMethods = new ArrayList<>();

            for (Method method : methods) {
                if (!isMethodPropertyAnnotated(method)) filteredMethods.add(method);
            }

            return filteredMethods;
        }

        List<Method> filteredMethods = new ArrayList<>();

        for (Field field : extractActualFields()) {
            PropertyDescriptor propertyDescriptor = getFieldPropertyDescriptor(field.getName());

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
            PropertyDescriptor propertyDescriptor = getFieldPropertyDescriptor(field.getName());

            if (propertyDescriptor == null) continue;
            Method writeMethod = propertyDescriptor.getWriteMethod();
            if (writeMethod != null && !writeMethod.isAnnotationPresent(ExcludeDFParam.class))
                filteredMethods.add(writeMethod);
        }

        return filteredMethods;
    }

    private PropertyDescriptor getFieldPropertyDescriptor(String fieldName) {
        PropertyDescriptor propertyDescriptor = this.propertyDescriptorMap.get(fieldName);
        if (propertyDescriptor != null) return propertyDescriptor;

        propertyDescriptor = BeanUtils.getPropertyDescriptor(this.beanClass, fieldName);

        this.propertyDescriptorMap.put(fieldName, propertyDescriptor);
        return propertyDescriptor;
    }

    private boolean isMethodPropertyAnnotated(Method method) throws NoSuchFieldException {
        PropertyDescriptor propertyDescriptor = BeanUtils.findPropertyForMethod(method, this.beanClass);
        if (propertyDescriptor == null)
            throw new IllegalArgumentException("DFAdapter must be specified only on type, field, getter or setter");

        Field annotatedField = this.beanClass.getDeclaredField(propertyDescriptor.getName());
        annotatedField.setAccessible(true);

        return annotatedField.isAnnotationPresent(DFParam.class);
    }
}
