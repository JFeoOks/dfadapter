package jfeoks.api.pojo;

public interface DataFlowParameterAdapter<S, D> {
    D convert(S source, PropertySource props);
}