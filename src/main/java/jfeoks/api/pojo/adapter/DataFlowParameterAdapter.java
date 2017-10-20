package jfeoks.api.pojo.adapter;

import jfeoks.api.pojo.ExpressionPropertySource;

public interface DataFlowParameterAdapter<S, D> {
    D convert(S source, ExpressionPropertySource props);
}