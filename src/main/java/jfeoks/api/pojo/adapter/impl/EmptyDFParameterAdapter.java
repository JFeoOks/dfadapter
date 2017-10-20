package jfeoks.api.pojo.adapter.impl;

import jfeoks.api.pojo.ExpressionPropertySource;
import jfeoks.api.pojo.adapter.DataFlowParameterAdapter;

public class EmptyDFParameterAdapter implements DataFlowParameterAdapter<Object, Object> {
    @Override
    public Object convert(Object source, ExpressionPropertySource props) {
        return source;
    }
}
