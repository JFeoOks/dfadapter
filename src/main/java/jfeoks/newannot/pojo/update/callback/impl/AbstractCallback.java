package jfeoks.newannot.pojo.update.callback.impl;

import jfeoks.newannot.pojo.nested.DFParamAdapter;
import jfeoks.newannot.pojo.update.ExpressionPropertySource;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.annotation.PropertyValue;
import jfeoks.newannot.pojo.update.callback.AccessibleObjectCallback;
import jfeoks.newannot.pojo.update.config.StaticContextHolder;

import java.lang.reflect.AccessibleObject;

public abstract class AbstractCallback implements AccessibleObjectCallback {

    @SuppressWarnings("unchecked")
    public  <T extends AccessibleObject> Object convertValueForRead(T accessibleObject, Object value) throws IllegalAccessException, InstantiationException {
        if (accessibleObject.isAnnotationPresent(DFParamAdapter.class)) {
            Class<? extends jfeoks.newannot.pojo.nested.DataFlowParameterAdapter> aClass = accessibleObject.getAnnotation(DFParamAdapter.class).adapterClass();

            jfeoks.newannot.pojo.nested.DataFlowParameterAdapter converter = aClass.newInstance();
            return converter.convert(value);
        } else if (accessibleObject.isAnnotationPresent(DFBidirectionalParamAdapter.class)) {
            DFBidirectionalParamAdapter adapter = accessibleObject.getAnnotation(DFBidirectionalParamAdapter.class);
            Class<? extends jfeoks.newannot.pojo.update.DataFlowParameterAdapter> aClass = adapter.readAdapterClass();

            jfeoks.newannot.pojo.update.DataFlowParameterAdapter converter = aClass.newInstance();
            ExpressionPropertySource properties = buildPropertySource(adapter.propertyValues());

            return converter.convert(value, properties);
        }

        return value;
    }

    public  <T extends AccessibleObject> Object convertValueForWrite(T accessibleObject, Object value) throws IllegalAccessException, InstantiationException {
        if (accessibleObject.isAnnotationPresent(DFParamAdapter.class)) {
            Class<? extends jfeoks.newannot.pojo.nested.DataFlowParameterAdapter> aClass = accessibleObject.getAnnotation(DFParamAdapter.class).adapterClass();

            jfeoks.newannot.pojo.nested.DataFlowParameterAdapter converter = aClass.newInstance();
            return converter.convert(value);
        } else if (accessibleObject.isAnnotationPresent(DFBidirectionalParamAdapter.class)) {
            DFBidirectionalParamAdapter adapter = accessibleObject.getAnnotation(DFBidirectionalParamAdapter.class);
            Class<? extends jfeoks.newannot.pojo.update.DataFlowParameterAdapter> aClass = adapter.writeAdapterClass();

            jfeoks.newannot.pojo.update.DataFlowParameterAdapter converter = aClass.newInstance();
            ExpressionPropertySource properties = buildPropertySource(adapter.propertyValues());

            return converter.convert(value, properties);
        }

        return value;
    }

    public ExpressionPropertySource buildPropertySource(PropertyValue[] propertyValues) {
        //TODO It's not a good solution
        ExpressionPropertySource expressionPropertySource = StaticContextHolder.getBean(ExpressionPropertySource.class);

        for (PropertyValue value : propertyValues) {
            expressionPropertySource.put(value.name(), value.spelExpression());
        }

        return expressionPropertySource;
    }
}
