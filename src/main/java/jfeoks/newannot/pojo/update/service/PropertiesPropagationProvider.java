package jfeoks.newannot.pojo.update.service;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.nested.Properties;
import jfeoks.newannot.pojo.update.callback.WriterCallback;

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
