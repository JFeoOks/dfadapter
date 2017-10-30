package jfeoks.newannot.pojo.update.callback;

import jfeoks.newannot.pojo.nested.MockStartParametersBuilder;
import jfeoks.newannot.pojo.nested.ShowPropertyPolicy;
import jfeoks.newannot.pojo.update.callback.pojo.ReaderAnnotatedPojo;
import jfeoks.newannot.pojo.update.extractor.impl.ParamsExtractorFactory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

public class ReaderCallbackTest {

    @Test
    public void checkAnnotatedFieldNotConvertedRightWay() throws Exception {
        ReaderAnnotatedPojo readerAnnotatedPojo = new ReaderAnnotatedPojo();
        MockStartParametersBuilder builder = new MockStartParametersBuilder();

        ReaderCallback readerCallback = new ReaderCallback(
                readerAnnotatedPojo,
                builder,
                false
        );

        Field annotatedString = readerAnnotatedPojo.getClass().getDeclaredField("annotatedString");
        annotatedString.setAccessible(true);
        readerCallback.doWith(annotatedString);

        assertEquals(builder.getPropertyName(), "testAnnotatedString");
        assertEquals(builder.getShowPropertyPolicy(), ShowPropertyPolicy.SHOW);
        assertFalse(builder.isMdcAware());
    }

    @Test
    public void checkAnnotatedMethodNotConvertedRightWay() throws Exception {
        ReaderAnnotatedPojo readerAnnotatedPojo = new ReaderAnnotatedPojo();
        MockStartParametersBuilder builder = new MockStartParametersBuilder();

        ReaderCallback readerCallback = new ReaderCallback(
                readerAnnotatedPojo,
                builder,
                true
        );

        List<Method> methods = ParamsExtractorFactory.newInstances(readerAnnotatedPojo.getClass()).extractGetMethods();
        Method annotatedMethod = methods.get(0);

        readerCallback.doWith(annotatedMethod);

        assertEquals(builder.getPropertyName(), "testGetAnnotatedMethodFieldBigInteger");
        assertNull(builder.getValue());
        assertEquals(builder.getShowPropertyPolicy(), ShowPropertyPolicy.MASK);
        assertTrue(builder.isMdcAware());
    }
}
