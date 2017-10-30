package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.POJOProcessor;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotatedParamsExtractor implements ParamsExtractor {

    private Class<?> beanClass;

    protected AnnotatedParamsExtractor(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    @Override
    public List<Field> extractFields() throws Exception {
        return POJOProcessor.getFieldsCache().get(beanClass);
    }

    @Override
    public List<Method> extractGetMethods() throws Exception {
        List<Method> resultMethods = new ArrayList<>();
        List<Method> rawMethods = POJOProcessor.getMethodsCache().get(beanClass);

        for (Method method : rawMethods) {
            if (isGetter(method)) resultMethods.add(method);
        }
        return resultMethods;
    }

    @Override
    public List<Method> extractSetMethods() throws Exception {
        List<Method> resultMethods = new ArrayList<>();
        List<Method> rawMethods = POJOProcessor.getMethodsCache().get(beanClass);

        for (Method method : rawMethods) {
            if (isSetter(method)) resultMethods.add(method);
        }

        return resultMethods;
    }

    protected static boolean isSetter(Method method) {
        return method.getParameterTypes().length == 1;
    }

    protected static boolean isGetter(Method method) {
        return method.getParameterTypes().length == 0 && !void.class.equals(method.getReturnType());
    }
}
