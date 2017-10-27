package jfeoks.newannot.pojo.nested;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Iterator;
import java.util.Set;

public class MockStartParameters implements Properties {
    @Override
    public boolean isVariableDeclared(String name) {
        return false;
    }

    @Override
    public void declareVariable(String name, Object value) {

    }

    @Override
    public void declareVariable(String name) {

    }

    @Override
    public <T extends Properties> void mergeTo(T properties) {

    }

    @Override
    public void closeAll() {

    }

    @Override
    public void setProperty(String name, String value, boolean pushUp) {

    }

    @Override
    public void setVariable(String name, Object value, boolean pushUp) {

    }

    @Override
    public <T> T getVariable(String name, Class<T> destClass) {
        return null;
    }

    @Override
    public void clearAll() {

    }

    @Override
    public void setProperty(String name, String value) {

    }

    @Override
    public void setVariable(String name, Object value) {

    }

    @Override
    public <T> void put(String name, T value) {

    }

    @Override
    public <T> T get(String name) {
        return null;
    }

    @Override
    public String getProperty(String name) {
        return null;
    }

    @Override
    public Object getVariable(String name) {
        return null;
    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public Set<String> getVariableNames() {
        return null;
    }

    @Override
    public Iterator<String> getNameIterator() {
        return null;
    }

    @Override
    public <T extends Variables> void mergeTo(T properties) {

    }

    @Override
    public <T extends Variables> void mergeTo(T properties, Iterable<String> names) {

    }

    @Override
    public void close() {

    }

    @Override
    public void remove(String name) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void setSerializeXmlVariables(boolean serializeXmlVariables) {

    }

    @Override
    public boolean getSerializeXmlVariables() {
        return false;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
