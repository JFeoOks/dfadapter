package jfeoks.old.api.pojo.adapter.impl;

import jfeoks.old.api.pojo.ExpressionPropertySource;
import jfeoks.old.api.pojo.adapter.DataFlowParameterAdapter;

public class HelloWorldDataFlowParameterAdapter implements DataFlowParameterAdapter<String, String> {

    @Override
    public String convert(String source, ExpressionPropertySource props) {
        String helloWorldProperty = props.get("helloWorldProperty", String.class);
        return source + " " + helloWorldProperty +  " , is I smiled? " + props.get("smile");
    }
}
