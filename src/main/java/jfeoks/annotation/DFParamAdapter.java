package jfeoks.annotation;

import jfeoks.api.pojo.DataFlowParameterAdapter;
import jfeoks.api.pojo.EmptyDFParameterAdapter;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DFParamAdapter {
    Class<? extends DataFlowParameterAdapter> adapterClass() default EmptyDFParameterAdapter.class;

    PropertyValue[] propertyValues() default {};
}
