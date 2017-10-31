package jfeoks.newannot.pojo.nested;

import java.util.HashMap;
import java.util.Map;

public class MockStartParametersBuilder {

    private boolean asynchronous;

    private Map<String, ValueHolder> properties = new HashMap<>();

    public void declareVariable(String propertyName, boolean b) {
        properties.put(
          propertyName,
          new ValueHolder(propertyName, null, b)
        );
    }

    public void setVariable(String propertyName, Object value, boolean b) {
        properties.put(
                propertyName,
                new ValueHolder(propertyName, value, b)
        );
    }

    public void setShowPropertyPolicy(String propertyName, ShowPropertyPolicy showPropertyPolicy) {
        properties.get(propertyName).setShowPropertyPolicy(showPropertyPolicy);
    }

    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }

    public static MockStartParametersBuilder newInstance() {
        return new MockStartParametersBuilder();
    }

    public Map<String, ValueHolder> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, ValueHolder> properties) {
        this.properties = properties;
    }

    public MockStartParameters build() {
        return null;
    }

    public void setVariable(String effectiveName, Object obj) {

    }
}

