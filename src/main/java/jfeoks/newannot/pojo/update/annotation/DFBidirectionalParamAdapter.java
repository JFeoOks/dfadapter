package jfeoks.newannot.pojo.update.annotation;

import jfeoks.newannot.pojo.update.DataFlowParameterAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DFBidirectionalParamAdapter {

    Class<? extends DataFlowParameterAdapter> readAdapterClass();

    Class<? extends DataFlowParameterAdapter> writeAdapterClass();

    PropertyValue[] propertyValues() default {};
}
