package jfeoks.old.api.pojo.adapter.impl;

import jfeoks.old.api.pojo.ExpressionPropertySource;
import jfeoks.old.api.pojo.adapter.DataFlowParameterAdapter;

public class EmptyDFParameterAdapter implements DataFlowParameterAdapter<Object, Object> {
    @Override
    public Object convert(Object source, ExpressionPropertySource props) {
        return source;
    }
}
