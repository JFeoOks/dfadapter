package jfeoks.newannot.pojo.update.extractor.impl.pojo;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.update.annotation.ExcludeDFParam;
import jfeoks.newannot.pojo.update.annotation.IncludeAllDFParams;

import java.math.BigInteger;

@IncludeAllDFParams
public class PojoDefaultTest {

//    ANNOTATED FIELDS

    @DFParam(name = "annotated_string")
    private String strAnnotated = "annotated string";

    @DFParam(name = "annotated_integer")
    Integer iAnnotated = 1;

    @DFParam(name = "annotated_boolean")
    protected boolean bAnnotated = true;

    @DFParam(name = "annotated_biginteger")
    public BigInteger bigIntegerAnnotated = new BigInteger("2");

//    NOT ANNOTATED FIELDS

    private String strNotAnnotated = "note annotated string";
    Integer iNotAnnotated = 1;
    protected boolean bNotAnnotated = true;
    public BigInteger bigIntegerNotAnnotated = new BigInteger("2");

//    ANNOTATED METHODS

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

//    NOT ANNOTATED METHODS

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

    @ExcludeDFParam
    private String excludedStr = "note annotated string";
    @ExcludeDFParam
    Integer excludedI = 1;
    @ExcludeDFParam
    protected boolean excludedB = true;
    @ExcludeDFParam
    public BigInteger excludedBiginteger = new BigInteger("2");

    private String excludedMethodStr = "note annotated string";
    Integer excludedMethodI = 1;
    protected boolean excludedMethodB = true;
    public BigInteger excludedMethodBiginteger = new BigInteger("2");

    @ExcludeDFParam
    public String getExcludedMethodStr() {
        return excludedMethodStr;
    }

    @ExcludeDFParam
    public void setExcludedMethodStr(String excludedMethodStr) {
        this.excludedMethodStr = excludedMethodStr;
    }

    @ExcludeDFParam
    public Integer getExcludedMethodI() {
        return excludedMethodI;
    }

    @ExcludeDFParam
    public void setExcludedMethodI(Integer excludedMethodI) {
        this.excludedMethodI = excludedMethodI;
    }

    @ExcludeDFParam
    public boolean isExcludedMethodB() {
        return excludedMethodB;
    }

    @ExcludeDFParam
    public void setExcludedMethodB(boolean excludedMethodB) {
        this.excludedMethodB = excludedMethodB;
    }

    @ExcludeDFParam
    public BigInteger getExcludedMethodBiginteger() {
        return excludedMethodBiginteger;
    }

    @ExcludeDFParam
    public void setExcludedMethodBiginteger(BigInteger excludedMethodBiginteger) {
        this.excludedMethodBiginteger = excludedMethodBiginteger;
    }

    //FIELD ANNOTATED BUT METHOD NOT

    @DFParam(name = "annotated_string_method")
    private String annotatedStrMethod = "note annotated string";

    @DFParam(name = "annotated_integer_method")
    Integer annotatedIMethod = 1;

    @DFParam(name = "annotated_boolean_method")
    protected boolean annotatedBMethod = true;

    @DFParam(name = "annotated_biginteger_method")
    public BigInteger annotatedBigIntegerMethod = new BigInteger("2");

    public String getAnnotatedStrMethod() {
        return annotatedStrMethod;
    }

    public void setAnnotatedStrMethod(String annotatedStrMethod) {
        this.annotatedStrMethod = annotatedStrMethod;
    }

    public Integer getAnnotatedIMethod() {
        return annotatedIMethod;
    }

    public void setAnnotatedIMethod(Integer annotatedIMethod) {
        this.annotatedIMethod = annotatedIMethod;
    }

    public boolean isAnnotatedBMethod() {
        return annotatedBMethod;
    }

    public void setAnnotatedBMethod(boolean annotatedBMethod) {
        this.annotatedBMethod = annotatedBMethod;
    }

    public BigInteger getAnnotatedBigIntegerMethod() {
        return annotatedBigIntegerMethod;
    }

    public void setAnnotatedBigIntegerMethod(BigInteger annotatedBigIntegerMethod) {
        this.annotatedBigIntegerMethod = annotatedBigIntegerMethod;
    }
}
