package jfeoks.newannot.pojo.update.extractor.impl.pojo;

import jfeoks.newannot.pojo.nested.DFParam;

import java.math.BigInteger;

public class PojoAnnotatedTest {

    @DFParam(name = "annotated_string")
    private String strAnnotated = "annotated string";

    @DFParam(name = "annotated_integer")
    Integer iAnnotated = 1;

    @DFParam(name = "annotated_boolean")
    protected boolean bAnnotated = true;

    @DFParam(name = "annotated_biginteger")
    public BigInteger bigIntegerAnnotated = new BigInteger("2");

    private String strNotAnnotated = "note annotated string";
    Integer iNotAnnotated = 1;
    protected boolean bNotAnnotated = true;
    public BigInteger bigIntegerNotAnnotated = new BigInteger("2");

    private String methodStr = "note annotated string";
    Integer methodI = 1;
    protected boolean methodB = true;
    public BigInteger methodBigInteger = new BigInteger("2");

    @DFParam(name = "annotated_getter_string")
    public String getMethodStr() {
        return methodStr;
    }

    @DFParam(name = "annotated_setter_string")
    public void setMethodStr(String methodStr) {
        this.methodStr = methodStr;
    }

    @DFParam(name = "annotated_getter_integer")
    public Integer getMethodI() {
        return methodI;
    }

    @DFParam(name = "annotated_setter_integer")
    public void setMethodI(Integer methodI) {
        this.methodI = methodI;
    }

    @DFParam(name = "annotated_getter_boolean")
    public boolean isMethodB() {
        return methodB;
    }

    @DFParam(name = "annotated_setter_boolean")
    public void setMethodB(boolean methodB) {
        this.methodB = methodB;
    }

    @DFParam(name = "annotated_getter_biginteger")
    public BigInteger getMethodBigInteger() {
        return methodBigInteger;
    }

    @DFParam(name = "annotated_setter_biginteger")
    public void setMethodBigInteger(BigInteger methodBigInteger) {
        this.methodBigInteger = methodBigInteger;
    }

    private String methodStrNotAnnotated = "note annotated string";
    Integer methodINotAnnotated = 1;
    protected boolean methodBNotAnnotated = true;
    public BigInteger methodBigIntegerNotAnnotated = new BigInteger("2");

    public String getMethodStrNotAnnotated() {
        return methodStrNotAnnotated;
    }

    public void setMethodStrNotAnnotated(String methodStrNotAnnotated) {
        this.methodStrNotAnnotated = methodStrNotAnnotated;
    }

    public Integer getMethodINotAnnotated() {
        return methodINotAnnotated;
    }

    public void setMethodINotAnnotated(Integer methodINotAnnotated) {
        this.methodINotAnnotated = methodINotAnnotated;
    }

    public boolean isMethodBNotAnnotated() {
        return methodBNotAnnotated;
    }

    public void setMethodBNotAnnotated(boolean methodBNotAnnotated) {
        this.methodBNotAnnotated = methodBNotAnnotated;
    }

    public BigInteger getMethodBigIntegerNotAnnotated() {
        return methodBigIntegerNotAnnotated;
    }

    public void setMethodBigIntegerNotAnnotated(BigInteger methodBigIntegerNotAnnotated) {
        this.methodBigIntegerNotAnnotated = methodBigIntegerNotAnnotated;
    }
}
