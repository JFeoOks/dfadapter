package jfeoks.api.pojo;

public class EmptyDFParameterAdapter implements DataFlowParameterAdapter<Object, Object> {
    @Override
    public Object convert(Object source, PropertySource props) {
        return source;
    }
}
