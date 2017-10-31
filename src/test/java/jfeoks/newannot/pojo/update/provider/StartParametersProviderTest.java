package jfeoks.newannot.pojo.update.provider;

import jfeoks.newannot.TestConfiguration;
import jfeoks.newannot.pojo.nested.MockStartParametersBuilder;
import jfeoks.newannot.pojo.nested.ValueHolder;
import jfeoks.newannot.pojo.update.provider.pojo.RequestAnnotatedTestPojo;
import jfeoks.newannot.pojo.update.provider.pojo.RequestDefaultTestPojo;
import jfeoks.newannot.pojo.update.provider.pojo.ResponseAnnotatedTestPojo;
import jfeoks.newannot.pojo.update.provider.pojo.ResponseDefaultTestPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class StartParametersProviderTest {

    @Test
    public void buildStartParametersWithAnnotatedRequestAndAnnotatedResponseRightWay() throws Exception {
        MockStartParametersBuilder startParametersBuilder = new MockStartParametersBuilder();
        StartParametersProvider startParametersProvider =
                new StartParametersProvider(
                        new RequestAnnotatedTestPojo(),
                        new ResponseAnnotatedTestPojo(),
                        startParametersBuilder
                );

        startParametersProvider.buildStartParameters();

        assertEquals(startParametersBuilder.getProperties().size(), 5);

        ValueHolder annotatedHelloField = startParametersBuilder.getProperties().get("annotated_hello_field");
        assertNotNull(annotatedHelloField);
        assertEquals(annotatedHelloField.getValue(), "Read string = Holly field; property source string = Hello World!");

        ValueHolder annotatedByeField = startParametersBuilder.getProperties().get("annotated_bye_field");
        assertNotNull(annotatedByeField);
        assertEquals(annotatedByeField.getValue(), "Read string = Holly field; property source string = Bye World!");

        ValueHolder annotatedStringMethod = startParametersBuilder.getProperties().get("annotated_string_method");
        assertNotNull(annotatedStringMethod);
        assertEquals(annotatedStringMethod.getValue(), "Read string = Holly method; property source string = Hello World!");

        ValueHolder annotatedResponseDeclaredOnlyField = startParametersBuilder.getProperties().get("annotated_response_declared_only_field");
        assertNotNull(annotatedResponseDeclaredOnlyField);
        assertNull(annotatedResponseDeclaredOnlyField.getValue());

        ValueHolder annotatedResponseDeclaredOnlyMethod = startParametersBuilder.getProperties().get("annotated_response_declared_only_method");
        assertNotNull(annotatedResponseDeclaredOnlyMethod);
        assertNull(annotatedResponseDeclaredOnlyMethod.getValue());
    }

    @Test
    public void buildStartParametersWithDefaultRequestAndDefaultResponseAccessTypeFieldRightWay() throws Exception {
        MockStartParametersBuilder startParametersBuilder = new MockStartParametersBuilder();
        StartParametersProvider startParametersProvider =
                new StartParametersProvider(
                        new RequestDefaultTestPojo(),
                        new ResponseDefaultTestPojo(),
                        startParametersBuilder
                );

        startParametersProvider.buildStartParameters();

        assertEquals(startParametersBuilder.getProperties().size(), 4);

        ValueHolder annotatedHelloField = startParametersBuilder.getProperties().get("annotated_hello_field");
        assertNotNull(annotatedHelloField);
        assertEquals(annotatedHelloField.getValue(), "Read string = Holly field; property source string = Hello World!");

        ValueHolder annotatedByeField = startParametersBuilder.getProperties().get("annotatedByeStringField");
        assertNotNull(annotatedByeField);
        assertEquals(annotatedByeField.getValue(), "Read string = Holly field; property source string = Bye World!");

        ValueHolder includedString = startParametersBuilder.getProperties().get("includedString");
        assertNotNull(includedString);
        assertEquals(includedString.getValue(), "Included field");

        ValueHolder annotatedOverridedStringMethod = startParametersBuilder.getProperties().get("annotated_overrided_string_method");
        assertNotNull(annotatedOverridedStringMethod);
        assertEquals(annotatedOverridedStringMethod.getValue(), "Read string = Holly method; property source string = Hello Overrided World!");

    }
}