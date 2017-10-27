package jfeoks.newannot.pojo.update.extractor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface ParamsExtractor {
    List<Field> extractFields(Class<?> beanClass) throws Exception;
    List<Method> extractMethods(Class<?> beanClass) throws Exception;
}
