package jfeoks.newannot.pojo.update.extractor;

import jfeoks.newannot.pojo.update.extractor.impl.AnnotatedParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.impl.DefaultParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.impl.PojoAnnotatedTest;
import jfeoks.newannot.pojo.update.extractor.impl.PojoDefaultTest;
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
