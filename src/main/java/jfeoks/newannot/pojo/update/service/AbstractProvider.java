package jfeoks.newannot.pojo.update.service;

import jfeoks.newannot.pojo.nested.AccessibleObjectCallback;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.impl.ParamsExtractorFactory;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractProvider {

    protected List<AccessibleObject> extractAccessibleObjects(Class<?> beanClass) throws Exception {
        ParamsExtractor paramsExtractor = ParamsExtractorFactory.newInstances(beanClass);
        List<AccessibleObject> accessibleObjects = new ArrayList<>();

        accessibleObjects.addAll(paramsExtractor.extractFields());
        accessibleObjects.addAll(paramsExtractor.extractSetMethods());

        return accessibleObjects;
    }

    protected <T extends AccessibleObject> void evaluateAccessibleObjects(
            List<T> accessibleObjects,
            AccessibleObjectCallback callback
    ) throws Exception {
        for (T accessibleObject : accessibleObjects) {
            callback.doWith(accessibleObject);
        }
    }
}
