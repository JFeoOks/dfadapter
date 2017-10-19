package jfeoks.api.pojo;

import jfeoks.annotation.DFParamAdapter;
import jfeoks.annotation.PropertyValue;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

@Component
public class DFAdapterProcessor {

    @Autowired
    private ObjectFactory<PropertySource> propertySourceFactory;

    public void process(Object obj) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Field helloField = obj.getClass().getField("helloField");
        System.out.println(convertFieldValue(helloField));
    }

    private <T extends AccessibleObject> Object convertFieldValue(T accessibleObject) throws IllegalAccessException, InstantiationException {
        if (!accessibleObject.isAnnotationPresent(DFParamAdapter.class)) return null;

        DFParamAdapter dfParamAdapter = accessibleObject.getAnnotation(DFParamAdapter.class);
        Class<? extends DataFlowParameterAdapter> clz = dfParamAdapter.adapterClass();
        DataFlowParameterAdapter adapter = clz.newInstance();

        PropertySource propertySource = buildPropertySource(dfParamAdapter.propertyValues());

        return adapter.convert("Okay", propertySource);
    }

    private PropertySource buildPropertySource(PropertyValue[] propertyValues) {
        PropertySource propertySource = propertySourceFactory.getObject();

        for (PropertyValue value : propertyValues) {
            propertySource.put(value.name(), value.spelExpression());
        }

        return propertySource;
    }
}
