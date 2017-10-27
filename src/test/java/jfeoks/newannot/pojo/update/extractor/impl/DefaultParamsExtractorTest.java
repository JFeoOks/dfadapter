package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.annotation.AccessType;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefaultParamsExtractorTest {

    @Test
    public void extractDefaultFieldsRightWayAccessTypeField() throws Exception {
        DefaultParamsExtractor defaultParamsExtractor = new DefaultParamsExtractor(AccessType.FIELD);

        List<Field> fields = defaultParamsExtractor.extractFields(PojoDefaultTest.class);

        assertEquals(fields.size(), 20);

        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("strAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("iAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("bAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("bigIntegerAnnotated")));

        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("strNotAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("iNotAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("bNotAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("bigIntegerNotAnnotated")));

        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodStr")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodI")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodB")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodBigInteger")));

        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodStrNotAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodINotAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodBNotAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("methodBigIntegerNotAnnotated")));

//        потому что accessType = FIELD => учитываем только на филде
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("excludedMethodStr")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("excludedMethodI")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("excludedMethodB")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("excludedMethodBiginteger")));
    }

    @Test
    public void extractDefaultMethodRightWayAccessTypeField() throws Exception {
        DefaultParamsExtractor defaultParamsExtractor = new DefaultParamsExtractor(AccessType.FIELD);

        List<Method> methods = defaultParamsExtractor.extractMethods(PojoDefaultTest.class);

        assertEquals(methods.size(), 4);

        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodStr", String.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodI", Integer.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodB", boolean.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodBigInteger", BigInteger.class)));
    }

    @Test
    public void extractDefaultFieldsRightWayAccessTypeMethod() throws Exception {
        DefaultParamsExtractor defaultParamsExtractor = new DefaultParamsExtractor(AccessType.METHOD);

        List<Field> fields = defaultParamsExtractor.extractFields(PojoDefaultTest.class);

        assertEquals(fields.size(), 4);

        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("strAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("iAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("bAnnotated")));
        assertTrue(fields.contains(PojoDefaultTest.class.getDeclaredField("bigIntegerAnnotated")));
    }

    @Test
    public void extractDefaultMethodRightWayAccessTypeMethod() throws Exception {
        DefaultParamsExtractor defaultParamsExtractor = new DefaultParamsExtractor(AccessType.METHOD);

        List<Method> methods = defaultParamsExtractor.extractMethods(PojoDefaultTest.class);
        assertEquals(methods.size(), 8);

        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodStr", String.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodI", Integer.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodB", boolean.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodBigInteger", BigInteger.class)));

        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodStrNotAnnotated", String.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodINotAnnotated", Integer.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodBNotAnnotated", boolean.class)));
        assertTrue(methods.contains(PojoDefaultTest.class.getDeclaredMethod("setMethodBigIntegerNotAnnotated", BigInteger.class)));
    }
}
