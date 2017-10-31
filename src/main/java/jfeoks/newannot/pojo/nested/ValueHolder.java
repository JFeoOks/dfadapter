package jfeoks.newannot.pojo.nested;

public class ValueHolder {

    private String propertyName;
    private Object value;
    private ShowPropertyPolicy showPropertyPolicy;
    private boolean mdcAware = false;

    public ValueHolder(String propertyName, Object value, ShowPropertyPolicy showPropertyPolicy, boolean mdcAware) {
        this.propertyName = propertyName;
        this.value = value;
        this.showPropertyPolicy = showPropertyPolicy;
        this.mdcAware = mdcAware;
    }

    public ValueHolder(String propertyName, Object value, boolean mdcAware) {
        this.propertyName = propertyName;
        this.value = value;
        this.mdcAware = mdcAware;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ValueHolder that = (ValueHolder) o;

        if (mdcAware != that.mdcAware) return false;
        if (propertyName != null ? !propertyName.equals(that.propertyName) : that.propertyName != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return showPropertyPolicy == that.showPropertyPolicy;
    }

    @Override
    public int hashCode() {
        int result = propertyName != null ? propertyName.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (showPropertyPolicy != null ? showPropertyPolicy.hashCode() : 0);
        result = 31 * result + (mdcAware ? 1 : 0);
        return result;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
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
}
