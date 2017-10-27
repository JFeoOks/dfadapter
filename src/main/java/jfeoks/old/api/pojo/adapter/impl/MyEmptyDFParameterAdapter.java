package jfeoks.old.api.pojo.adapter.impl;

import jfeoks.old.api.pojo.ExpressionPropertySource;
import jfeoks.old.api.pojo.adapter.MyDataFlowParameterAdapter;

public class MyEmptyDFParameterAdapter implements MyDataFlowParameterAdapter<Object, Object> {
    @Override
    public Object convert(Object source, ExpressionPropertySource props) {
        return source;
    }
}
