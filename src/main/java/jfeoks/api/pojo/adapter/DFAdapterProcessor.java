package jfeoks.api.pojo.adapter;

import jfeoks.annotation.DFParamAdapter;
import jfeoks.annotation.PropertyValue;
import jfeoks.api.pojo.ExpressionPropertySource;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.*;

@Component
public class DFAdapterProcessor {

    @Autowired
    private ObjectFactory<ExpressionPropertySource> propertySourceFactory;

    public void process(Object obj) throws NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Field helloField = obj.getClass().getField("helloField");
        System.out.println(readValue(helloField));
    }

    private <T extends AccessibleObject> Object readValue(T accessibleObject) throws IllegalAccessException, InstantiationException, NoSuchMethodException {
        if (!accessibleObject.isAnnotationPresent(DFParamAdapter.class)) return null;

        Object variable = "I said";

        DFParamAdapter dfParamAdapter = accessibleObject.getAnnotation(DFParamAdapter.class);
        Class<? extends DataFlowParameterAdapter> clz = dfParamAdapter.adapterClass();

        Class<?> fieldType = getFieldType(accessibleObject);

        if (!fieldType.isAssignableFrom(variable.getClass())) throw new IllegalArgumentException("Incorrect a converter input type");

        DataFlowParameterAdapter adapter = clz.newInstance();
        ExpressionPropertySource expressionPropertySource = buildPropertySource(dfParamAdapter.propertyValues());

        return adapter.convert(variable, expressionPropertySource);
    }

    private ExpressionPropertySource buildPropertySource(PropertyValue[] propertyValues) {
        ExpressionPropertySource expressionPropertySource = propertySourceFactory.getObject();

        for (PropertyValue value : propertyValues) {
            expressionPropertySource.put(value.name(), value.spelExpression());
        }

        return expressionPropertySource;
    }

    private Class<?> getFieldType(AccessibleObject accessibleObject) {
        if (accessibleObject instanceof Field) {
            Field field = (Field) accessibleObject;
            return field.getType();
        } else if (accessibleObject instanceof Method) {
            Method method = (Method) accessibleObject;
            return method.getReturnType();
        }

        return ObjectUtils.Null.class;
    }
}
