package jfeoks.old.annotation.adapter;

import jfeoks.old.api.pojo.adapter.MyDataFlowParameterAdapter;
import jfeoks.old.api.pojo.adapter.impl.MyEmptyDFParameterAdapter;

import java.lang.annotation.*;

@Inherited
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDfParamAdapter {
    Class<? extends MyDataFlowParameterAdapter<?, ?>>[] readAdapterClass() default MyEmptyDFParameterAdapter.class;

    Class<? extends MyDataFlowParameterAdapter<?, ?>>[] writeAdapterClass() default MyEmptyDFParameterAdapter.class;

    PropertyValue[] propertyValues() default {};
}