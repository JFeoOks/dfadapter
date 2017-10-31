package jfeoks.newannot.pojo.update.propagator.impl;

import jfeoks.newannot.pojo.update.propagator.PropertyPropagator;

import java.lang.reflect.Method;

public class MethodPropertyPropagator implements PropertyPropagator<Method> {
    @Override
    public void propagate(Object target, Method aObject, Object value) throws Exception {
        aObject.invoke(target, value);
    }
}
