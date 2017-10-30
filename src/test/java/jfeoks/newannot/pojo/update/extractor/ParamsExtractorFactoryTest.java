package jfeoks.newannot.pojo.update.extractor;

import jfeoks.newannot.pojo.update.extractor.impl.*;
import jfeoks.newannot.pojo.update.extractor.impl.pojo.PojoAnnotatedTest;
import jfeoks.newannot.pojo.update.extractor.impl.pojo.PojoDefaultTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParamsExtractorFactoryTest {

    @Test
    public void ifIncludeAnnotationPresentThenDefaultExtractor() {
        assertTrue(ParamsExtractorFactory.newInstances(PojoDefaultTest.class) instanceof DefaultParamsExtractor);
    }

    @Test
    public void ifAnnotationNotPresentThenAnnotatedParamsExtractor() {
        assertTrue(ParamsExtractorFactory.newInstances(PojoAnnotatedTest.class) instanceof AnnotatedParamsExtractor);
    }
}
