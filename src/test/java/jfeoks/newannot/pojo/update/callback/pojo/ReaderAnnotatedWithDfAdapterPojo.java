package jfeoks.newannot.pojo.update.callback.pojo;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.nested.ShowPropertyPolicy;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.annotation.PropertyValue;

import java.math.BigInteger;

public class ReaderAnnotatedWithDfAdapterPojo {

    @DFParam(name = "testAnnotatedString")
    @DFBidirectionalParamAdapter(
            readAdapterClass = MockReadAdapter.class,
            writeAdapterClass = MockWriterAdapter.class,
            propertyValues = {
                    @PropertyValue(name = "hello", spelExpression = "'Hello World!'")
            }
    )
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
