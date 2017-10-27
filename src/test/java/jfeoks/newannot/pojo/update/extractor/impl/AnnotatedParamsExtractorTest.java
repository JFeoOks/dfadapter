package jfeoks.newannot.pojo.update.extractor.impl;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class AnnotatedParamsExtractorTest {

    @Test
    public void extractAnnotatedFieldsRightWay() throws Exception {
        AnnotatedParamsExtractor annotatedParamsExtractor = new AnnotatedParamsExtractor();

        List<Field> fields = annotatedParamsExtractor.extractFields(PojoAnnotatedTest.class);

        assertEquals(fields.size(), 4);

        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("strAnnotated")));
        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("iAnnotated")));
        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("bAnnotated")));
        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("bigIntegerAnnotated")));

        assertFalse(fields.contains(PojoAnnotatedTest.class.getDeclaredField("strNotAnnotated")));
        assertFalse(fields.contains(PojoAnnotatedTest.class.getDeclaredField("iNotAnnotated")));
        assertFalse(fields.contains(PojoAnnotatedTest.class.getDeclaredField("bNotAnnotated")));
        assertFalse(fields.contains(PojoAnnotatedTest.class.getDeclaredField("bigIntegerNotAnnotated")));
    }

    @Test
    public void extractAnnotatedMethodRightWay() throws Exception {
        AnnotatedParamsExtractor annotatedParamsExtractor = new AnnotatedParamsExtractor();

        List<Method> methods = annotatedParamsExtractor.extractMethods(PojoAnnotatedTest.class);

        assertEquals(methods.size(), 4);

        assertTrue(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodStr", String.class)));
        assertTrue(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodI", Integer.class)));
        assertTrue(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodB", boolean.class)));
        assertTrue(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodBigInteger", BigInteger.class)));

        assertFalse(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodStrNotAnnotated", String.class)));
        assertFalse(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodINotAnnotated", Integer.class)));
        assertFalse(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodBNotAnnotated", boolean.class)));
        assertFalse(methods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodBigIntegerNotAnnotated", BigInteger.class)));
    }


}
