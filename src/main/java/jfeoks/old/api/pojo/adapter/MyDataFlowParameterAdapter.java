package jfeoks.old.api.pojo.adapter;

import jfeoks.old.api.pojo.ExpressionPropertySource;

public interface MyDataFlowParameterAdapter<S, D> {
    D convert(S source, ExpressionPropertySource props);
}