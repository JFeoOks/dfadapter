package jfeoks.newannot.pojo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DFBiderecrtionalParamAdapter {

    Class<? extends DataFlowParameterAdapter> readAdapterClass() default EmptyDFParameterAdapter.class;

    Class<? extends DataFlowParameterAdapter> writeAdapterClass() default EmptyDFParameterAdapter.class;

    PropertyValue[] propertyValues() default {};
}
