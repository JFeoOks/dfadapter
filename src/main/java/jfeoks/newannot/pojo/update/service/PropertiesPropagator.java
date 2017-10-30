package jfeoks.newannot.pojo.update.service;

import jfeoks.newannot.pojo.nested.AccessibleObjectCallback;
import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.nested.Properties;
import jfeoks.newannot.pojo.update.callback.WriterCallback;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractorFactory;

import java.lang.reflect.Field;
import java.util.List;


public class PropertiesPropagator {

    private Object target;
    private Properties startParameters;

    public PropertiesPropagator(Object target, Properties startParameters) {
        this.target = target;
        this.startParameters = startParameters;
    }

    public void propagate() {
        Class<?> beanClass = target.getClass();

        if (beanClass.isAnnotationPresent(DFParam.class)) {
            DFParam dfParam = beanClass.getAnnotation(DFParam.class);
            String effectiveName = dfParam.name();
            if (startParameters.getVariable(effectiveName) != target)
                target = startParameters.getVariable(effectiveName);
        }

        ParamsExtractor paramsExtractor = ParamsExtractorFactory.newInstances(beanClass);

        try {
            evaluateFields(
                    paramsExtractor.extractFields(beanClass),
                    new WriterCallback(startParameters, target)
            );
        } catch (Exception e) {
            //TODO
        }
    }

    private void evaluateFields(List<Field> fields, AccessibleObjectCallback callback) throws Exception {
        for (Field field : fields) {
            callback.doWith(field);
        }
    }
}
