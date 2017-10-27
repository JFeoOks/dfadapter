package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.POJOProcessor;
import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnnotatedParamsExtractor implements ParamsExtractor {

    @Override
    public List<Field> extractFields(Class<?> beanClass) throws Exception {
        return POJOProcessor.getFieldsCache().get(beanClass);
    }

    @Override
    public List<Method> extractMethods(Class<?> beanClass) throws Exception {
        return POJOProcessor.getMethodsCache().get(beanClass);
    }
}
