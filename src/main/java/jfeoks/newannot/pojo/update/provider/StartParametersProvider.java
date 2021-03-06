package jfeoks.newannot.pojo.update.provider;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.nested.*;
import jfeoks.newannot.pojo.update.callback.impl.ReaderCallback;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.impl.ParamsExtractorFactory;

import java.lang.reflect.AccessibleObject;
import java.util.ArrayList;
import java.util.List;

public class StartParametersProvider extends AbstractProvider {

    private Object request;

    private Object response;

    private MockStartParametersBuilder startParametersBuilder;

    public StartParametersProvider(
            Object request,
            Object response,
            MockStartParametersBuilder startParametersBuilder) {

        this.request = request;
        this.response = response;
        this.startParametersBuilder = startParametersBuilder;
    }

    public StartParametersProvider(
            Object request,
            Object response
    ) {
        this.request = request;
        this.response = response;
        this.startParametersBuilder = MockStartParametersBuilder.newInstance();
    }

    public MockStartParameters buildStartParameters() throws Exception {
        readRequestParameters();
        readResponseParameters();
        return startParametersBuilder.build();
    }

    private Class<?> evaluateClass(Object obj) {
        Class<?> beanClass = obj.getClass();

        if (beanClass.isAnnotationPresent(DFParam.class)) {
            DFParam dfParam = beanClass.getAnnotation(DFParam.class);
            String effectiveName = dfParam.name();
            startParametersBuilder.setVariable(effectiveName, obj);
        }

        return beanClass;
    }

    private void readRequestParameters() throws Exception {
        Class<?> requestClass = evaluateClass(this.request);

        evaluateAccessibleObjects(
                extractAccessibleObjects(requestClass),
                new ReaderCallback(this.request, this.startParametersBuilder, false)
        );
    }

    private void readResponseParameters() throws Exception {
        Class<?> responseClass = evaluateClass(this.response);

        evaluateAccessibleObjects(
                extractAccessibleObjects(responseClass),
                new ReaderCallback(this.response, this.startParametersBuilder, true)
        );
    }

    private List<AccessibleObject> extractAccessibleObjects(Class<?> beanClass) throws Exception {
        ParamsExtractor paramsExtractor = ParamsExtractorFactory.newInstances(beanClass);
        List<AccessibleObject> accessibleObjects = new ArrayList<>();

        accessibleObjects.addAll(paramsExtractor.extractFields());
        accessibleObjects.addAll(paramsExtractor.extractGetMethods());

        return accessibleObjects;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public MockStartParametersBuilder getStartParametersBuilder() {
        return startParametersBuilder;
    }

    public void setStartParametersBuilder(MockStartParametersBuilder startParametersBuilder) {
        this.startParametersBuilder = startParametersBuilder;
    }
}

