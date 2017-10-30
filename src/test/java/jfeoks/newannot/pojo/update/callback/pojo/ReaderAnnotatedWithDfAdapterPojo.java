package jfeoks.newannot.pojo.update.callback.pojo;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.nested.ShowPropertyPolicy;

import java.math.BigInteger;

//TODO Доделать тест с DF Adapter'ом
public class ReaderAnnotatedWithDfAdapterPojo {

    @DFParam(name = "testAnnotatedString")
    private String annotatedString = "annotated string";

    private BigInteger annotatedMethodFieldBigInteger = new BigInteger("123");

    @DFParam(name = "testSetAnnotatedMethodFieldBigInteger", showPropertyPolicy = ShowPropertyPolicy.MASK)
    public void setAnnotatedMethodFieldBigInteger(BigInteger annotatedMethodFieldBigInteger) {
        this.annotatedMethodFieldBigInteger = annotatedMethodFieldBigInteger;
    }

    @DFParam(name = "testGetAnnotatedMethodFieldBigInteger", showPropertyPolicy = ShowPropertyPolicy.MASK, mdcAware = true)
    public BigInteger getAnnotatedMethodFieldBigInteger() {
        return annotatedMethodFieldBigInteger;
    }
}
