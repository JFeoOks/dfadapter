package jfeoks.newannot.pojo.update.service;

import jfeoks.newannot.pojo.nested.DFParam;
import jfeoks.newannot.pojo.nested.*;
import jfeoks.newannot.pojo.update.callback.ReaderCallback;
import jfeoks.newannot.pojo.update.extractor.ParamsExtractor;
import jfeoks.newannot.pojo.update.extractor.impl.ParamsExtractorFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class StartParametersProvider {

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

    private void evaluateClass(Object obj) {
        Class<?> beanClass = obj.getClass();

        if (beanClass.isAnnotationPresent(DFParam.class)) {
            DFParam dfParam = beanClass.getAnnotation(DFParam.class);
            String effectiveName = dfParam.name();
            startParametersBuilder.setVariable(effectiveName, obj);
        }
    }

    private void evaluateFields(List<Field> fields, AccessibleObjectCallback callback) throws Exception {
        for (Field field : fields) {
            callback.doWith(field);
        }
    }

    private void evaluateMethods(List<Method> methods, AccessibleObjectCallback callback) throws Exception {
        for (Method method : methods) {
            callback.doWith(method);
        }
    }

    private void readRequestParameters() throws Exception {
        Class<?> requestClass = this.request.getClass();
        evaluateClass(requestClass);

        ParamsExtractor paramsExtractor = ParamsExtractorFactory.newInstances(requestClass);

        evaluateFields(
                paramsExtractor.extractFields(),
                new ReaderCallback(this.request, this.startParametersBuilder, false)
        );

        evaluateMethods(
                paramsExtractor.extractGetMethods(),
                new ReaderCallback(this.request, this.startParametersBuilder, false)
        );
    }

    private void readResponseParameters() throws Exception {
        Class<?> responseClass = this.response.getClass();
        evaluateClass(responseClass);

        ParamsExtractor paramsExtractor = ParamsExtractorFactory.newInstances(responseClass);

        evaluateFields(
                paramsExtractor.extractFields(),
                new ReaderCallback(this.request, this.startParametersBuilder, true)
        );

        evaluateMethods(
                paramsExtractor.extractSetMethods(),
                new ReaderCallback(this.request, this.startParametersBuilder, true)
        );
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

