package jfeoks.annotation;

import jfeoks.api.pojo.adapter.DataFlowParameterAdapter;
import jfeoks.api.pojo.adapter.impl.EmptyDFParameterAdapter;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DFParamAdapter {
    Class<? extends DataFlowParameterAdapter> adapterClass() default EmptyDFParameterAdapter.class;

    Class<? extends DataFlowParameterAdapter> BackAdapterClass() default EmptyDFParameterAdapter.class;

    PropertyValue[] propertyValues() default {};
}
