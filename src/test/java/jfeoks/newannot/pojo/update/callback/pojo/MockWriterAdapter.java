package jfeoks.newannot.pojo.update.callback.pojo;

import jfeoks.newannot.pojo.update.DataFlowParameterAdapter;
import jfeoks.newannot.pojo.update.ExpressionPropertySource;

public class MockWriterAdapter implements DataFlowParameterAdapter<String, String> {

    @Override
    public String convert(String source, ExpressionPropertySource propertySource) {
        return "Write string = " + source;

    }
}
