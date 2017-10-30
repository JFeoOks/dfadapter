package jfeoks.newannot.pojo.update.callback;

import jfeoks.newannot.pojo.nested.MockStartParametersBuilder;
import jfeoks.newannot.pojo.nested.ShowPropertyPolicy;
import jfeoks.newannot.pojo.update.ExpressionPropertySource;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.callback.pojo.PropertySourceBuilderPojo;
import jfeoks.newannot.pojo.update.callback.pojo.ReaderAnnotatedPojo;
import jfeoks.newannot.pojo.update.callback.pojo.ReaderAnnotatedWithDfAdapterPojo;
import jfeoks.newannot.pojo.update.callback.pojo.TestConfiguration;
import jfeoks.newannot.pojo.update.extractor.impl.ParamsExtractorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
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

        Field annotatedString = ParamsExtractorFactory.newInstances(readerAnnotatedPojo.getClass()).extractFields().get(0);
        readerCallback.doWith(annotatedString);

        assertEquals(builder.getPropertyName(), "testAnnotatedString");
        assertEquals(builder.getValue(), "annotated string");
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

    @Test
    public void checkBuildPropertySourceRightWay() throws Exception {
        PropertySourceBuilderPojo mock = new PropertySourceBuilderPojo();
        MockStartParametersBuilder builder = new MockStartParametersBuilder();

        ReaderCallback readerCallback = new ReaderCallback(
                mock,
                builder,
                false
        );

        ExpressionPropertySource expressionPropertySource = readerCallback.buildPropertySource(
                PropertySourceBuilderPojo.class
                        .getDeclaredField("testString")
                        .getAnnotation(DFBidirectionalParamAdapter.class)
                        .propertyValues()
        );

        assertEquals(expressionPropertySource.size(), 3);

        assertEquals(expressionPropertySource.get("hello string"), "Hello World!");
        assertEquals(expressionPropertySource.get("bye string", String.class), "Bye World!");
        assertTrue(expressionPropertySource.get("bean test") instanceof PropertySourceBuilderPojo);
    }

    @Test
    public void checkAnnotatedFieldWithAdapterRightWay() throws Exception {
        ReaderAnnotatedWithDfAdapterPojo readerPojo = new ReaderAnnotatedWithDfAdapterPojo();
        MockStartParametersBuilder builder = new MockStartParametersBuilder();

        ReaderCallback readerCallback = new ReaderCallback(
                readerPojo,
                builder,
                false
        );

        Field annotatedString = readerPojo.getClass().getDeclaredField("annotatedString");
        annotatedString.setAccessible(true);

        readerCallback.doWith(annotatedString);

        assertEquals(builder.getPropertyName(), "testAnnotatedString");
        assertEquals(builder.getValue(), "Read string = annotated string; property source string = Hello World!");
        assertEquals(builder.getShowPropertyPolicy(), ShowPropertyPolicy.SHOW);
        assertFalse(builder.isMdcAware());
    }

}
