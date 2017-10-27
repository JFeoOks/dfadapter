package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotatedParamsExtractor implements ParamsExtractor {

    @Override
    public List<Field> extractFields(Class<?> beanClass) throws Exception {
        Field[] declaredFields = beanClass.getDeclaredFields();
        List<Field> filteredFields = new ArrayList<>();

        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(ExcludeDFParam.class)) filteredFields.add(field);
        }
        return filteredFields;
    }

    @Override
    public List<Method> extractMethods(Class<?> beanClass) throws Exception {
        Method[] declaredMethods = beanClass.getDeclaredMethods();
        List<Method> filteredMethods = new ArrayList<>();

        for (Method method : declaredMethods) {
            if (!method.isAnnotationPresent(ExcludeDFParam.class)) filteredMethods.add(method);
        }

        return filteredMethods;
    }
}
