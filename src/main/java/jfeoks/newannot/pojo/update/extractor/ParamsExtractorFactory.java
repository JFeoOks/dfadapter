package jfeoks.newannot.pojo.update.extractor;

import jfeoks.newannot.pojo.update.annotation.IncludeAllDFParams;
import jfeoks.newannot.pojo.update.extractor.impl.AnnotatedParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.impl.DefaultParamsExtractor;

public class ParamsExtractorFactory {

    public static ParamsExtractor newInstances(Class<?> beanClass) {
        if (beanClass.isAnnotationPresent(IncludeAllDFParams.class)) return new DefaultParamsExtractor();
        return new AnnotatedParamsExtractor();
    }
}
