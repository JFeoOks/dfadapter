package jfeoks.newannot.pojo.update.extractor.impl;

import jfeoks.newannot.pojo.update.extractor.impl.pojo.PojoAnnotatedTest;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

public class AnnotatedParamsExtractorTest {

    @Test
    public void extractAnnotatedFieldsRightWay() throws Exception {
        AnnotatedParamsExtractor annotatedParamsExtractor = new AnnotatedParamsExtractor(PojoAnnotatedTest.class);

        List<Field> fields = annotatedParamsExtractor.extractFields();

        assertEquals(fields.size(), 4);

        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("strAnnotated")));
        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("iAnnotated")));
        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("bAnnotated")));
        assertTrue(fields.contains(PojoAnnotatedTest.class.getDeclaredField("bigIntegerAnnotated")));
    }

    @Test
    public void extractAnnotatedSetMethodRightWay() throws Exception {
        AnnotatedParamsExtractor annotatedParamsExtractor = new AnnotatedParamsExtractor(PojoAnnotatedTest.class);

        List<Method> setMethods = annotatedParamsExtractor.extractSetMethods();

        assertEquals(setMethods.size(), 4);

        assertTrue(setMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodStr", String.class)));
        assertTrue(setMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodI", Integer.class)));
        assertTrue(setMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodB", boolean.class)));
        assertTrue(setMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("setMethodBigInteger", BigInteger.class)));
    }

    @Test
    public void extractAnnotatedGetMethodRightWay() throws Exception {
        AnnotatedParamsExtractor annotatedParamsExtractor = new AnnotatedParamsExtractor(PojoAnnotatedTest.class);

        List<Method> getMethods = annotatedParamsExtractor.extractGetMethods();

        assertEquals(getMethods.size(), 4);

        assertTrue(getMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("getMethodStr")));
        assertTrue(getMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("getMethodI")));
        assertTrue(getMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("isMethodB")));
        assertTrue(getMethods.contains(PojoAnnotatedTest.class.getDeclaredMethod("getMethodBigInteger")));
    }


}
