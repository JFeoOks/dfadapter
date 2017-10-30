package jfeoks.newannot.pojo.nested;

public class MockStartParametersBuilder {

    private String propertyName;
    private Object value;
    ShowPropertyPolicy showPropertyPolicy;
    boolean mdcAware = false;

    private boolean asynchronous;

    public void declareVariable(String propertyName, boolean b) {
        this.propertyName = propertyName;
        this.mdcAware = b;
    }

    public void setVariable(String propertyName, Object value, boolean b) {
        this.propertyName = propertyName;
        this.value = value;
        this.mdcAware = b;
    }

    public void setShowPropertyPolicy(String propertyName, ShowPropertyPolicy showPropertyPolicy) {
        this.propertyName = propertyName;
        this.showPropertyPolicy = showPropertyPolicy;
    }

    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }

    public static MockStartParametersBuilder newInstance() {
        return new MockStartParametersBuilder();
    }

    public void setVariable(String effectiveName, Object obj) {
        this.propertyName = effectiveName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public ShowPropertyPolicy getShowPropertyPolicy() {
        return showPropertyPolicy;
    }

    public void setShowPropertyPolicy(ShowPropertyPolicy showPropertyPolicy) {
        this.showPropertyPolicy = showPropertyPolicy;
    }

    public boolean isMdcAware() {
        return mdcAware;
    }

    public void setMdcAware(boolean mdcAware) {
        this.mdcAware = mdcAware;
    }

    public MockStartParameters build() {
        return null;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
