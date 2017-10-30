package jfeoks.newannot.pojo.update.callback.pojo;

import jfeoks.newannot.pojo.update.DataFlowParameterAdapter;
import jfeoks.newannot.pojo.update.ExpressionPropertySource;

public class MockReadAdapter implements DataFlowParameterAdapter<String, String> {

    @Override
    public String convert(String source, ExpressionPropertySource propertySource) {
        return "Read string = " + source;
    }
}
