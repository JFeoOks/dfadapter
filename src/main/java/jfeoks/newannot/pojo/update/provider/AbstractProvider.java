package jfeoks.newannot.pojo.update.provider;

import jfeoks.newannot.pojo.update.callback.AccessibleObjectCallback;

import java.lang.reflect.AccessibleObject;
import java.util.List;

public abstract class AbstractProvider {

    protected <T extends AccessibleObject> void evaluateAccessibleObjects(
            List<T> accessibleObjects,
            AccessibleObjectCallback callback
    ) throws Exception {
        for (T accessibleObject : accessibleObjects) {
            callback.doWith(accessibleObject);
        }
    }
}
