package jfeoks.newannot.pojo.update.provider;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.nested.Properties;
import jfeoks.newannot.pojo.update.callback.impl.WriterCallback;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.impl.ParamsExtractorFactory;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;

public class PropertiesPropagationProvider extends AbstractProvider {

    private Object target;
    private Properties startParameters;

    public PropertiesPropagationProvider(Object target, Properties startParameters) {
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

        try {
            evaluateAccessibleObjects(
                    extractAccessibleObjects(beanClass),
                    new WriterCallback(startParameters, target)
            );

        } catch (Exception e) {
            //TODO
        }
    }

    private List<AccessibleObject> extractAccessibleObjects(Class<?> beanClass) throws Exception {
        ParamsExtractor paramsExtractor = ParamsExtractorFactory.newInstances(beanClass);
        List<AccessibleObject> accessibleObjects = new ArrayList<>();

        accessibleObjects.addAll(paramsExtractor.extractFields());
        accessibleObjects.addAll(paramsExtractor.extractSetMethods());

        return accessibleObjects;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Properties getStartParameters() {
        return startParameters;
    }

    public void setStartParameters(Properties startParameters) {
        this.startParameters = startParameters;
    }
}
