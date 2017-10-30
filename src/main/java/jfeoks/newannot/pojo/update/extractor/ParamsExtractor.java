package jfeoks.newannot.pojo.update.extractor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface ParamsExtractor {
    List<Field> extractFields() throws Exception;

    List<Method> extractGetMethods() throws Exception;

    List<Method> extractSetMethods() throws Exception;
}
