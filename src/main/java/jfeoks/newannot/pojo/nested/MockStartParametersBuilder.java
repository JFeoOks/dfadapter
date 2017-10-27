package jfeoks.newannot.pojo.nested;

public class MockStartParametersBuilder {
    private boolean asynchronous;

    public void declareVariable(String propertyName, boolean b) {

    }

    public void setVariable(String propertyName, Object value, boolean b) {

    }

    public void setShowPropertyPolicy(String propertyName, ShowPropertyPolicy showPropertyPolicy) {

    }

    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }

    public static MockStartParametersBuilder newInstance() {
        return new MockStartParametersBuilder();
    }

    public void setVariable(String effectiveName, Object obj) {

    }

    public MockStartParameters build() {
        return null;
    }
}
