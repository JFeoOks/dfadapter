/*
 This software is the confidential information and copyrighted work of
 NetCracker Technology Corp. ("NetCracker") and/or its suppliers and
 is only distributed under the terms of a separate license agreement
 with NetCracker.
 Use of the software is governed by the terms of the license agreement.
 Any use of this software not in accordance with the license agreement
 is expressly prohibited by law, and may result in severe civil
 and criminal penalties. 
 
 Copyright (c) 1995-2017 NetCracker Technology Corp.
 
 All Rights Reserved.
 
*/
package jfeoks.newannot.pojo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.core.annotation.AnnotationUtils;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by ZiborovE on 26.12.2016.
 */
public class POJOProcessor {
    
    private static final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();

    private final static LoadingCache<Class, List<Field>> FIELDS_CACHE = buildFieldsCache(DFParam.class);
    private final static LoadingCache<Class, List<Method>> METHODS_CACHE = buildMethodsCache(DFParam.class);

    public static void process(Request object) throws Exception {
        process(object, object);
    }

    public static MockSessionHandler process(Request request, Object response) throws Exception {
        performValidation(request);
            MockStartParametersBuilder builder = MockStartParametersBuilder.newInstance();
            builder.setAsynchronous(request.isAsynchronous());
//            builder.setMDCContainer(mdcContainer);
            readFromObject(builder, request);
            readFromObject(builder, response, true);
            MockStartParameters startParameters = builder.build();
//            RootSession rootSession = DataFlowContextProvider.provide().dataFlowProcessor().createSession(request.getConfigID(), startParameters);
//            SessionHandler sessionHandler = DataFlowContextProvider.provide().dataFlowProcessor().startSession(rootSession);

//            if (request instanceof SessionAwareRequest)
//                ((SessionAwareRequest)request).setSessionHandler(sessionHandler);

            propogateProperties(response, startParameters);
            return new MockSessionHandler();
    }

    protected static void performValidation(Request request) {
        Set<ConstraintViolation<Request>> sets = vf.getValidator().validate(request);
        if (sets.size() != 0)
            throw new ConstraintViolationException("Incorrect request data passed to DataFlow POJOProcessor: " + sets,sets);
    }

    private static void readFromObject(MockStartParametersBuilder builder, Object obj) {
        readFromObject(builder, obj, false);
    }

    static void readFromObject(final MockStartParametersBuilder builder, final Object obj, final boolean declareOnly) {
        Class reqCls = obj.getClass();
        try {
            //doWithClass
            if (reqCls.isAnnotationPresent(DFParam.class)) {
                Annotation classAnn = reqCls.getAnnotation(DFParam.class);
                DFParam dfParam = (DFParam) classAnn;
                String effectiveName = dfParam.name();
                builder.setVariable(effectiveName, obj);
            }

            doWithFields(reqCls, new ReaderCallback(obj, builder, declareOnly));
            doWithMethods(reqCls, new ReaderCallback(obj, builder, declareOnly));
        } catch (Exception e) {
        }
    }

    static void propogateProperties(Object target, Properties startParameters) {
        Class reqCls = target.getClass();
        try {
            if (reqCls.isAnnotationPresent(DFParam.class)) {
                Annotation classAnn = reqCls.getAnnotation(DFParam.class);
                DFParam dfParam = (DFParam) classAnn;
                String effectiveName = dfParam.name();
                if (startParameters.getVariable(effectiveName) != target)
                    target = startParameters.getVariable(effectiveName);
            }

            doWithFields(reqCls, new WriterCallback(startParameters, target));
        } catch (Exception e) {
        }
    }

    public static LoadingCache<Class, List<Field>> buildFieldsCache(final Class<? extends Annotation> annotation) {
        return CacheBuilder.newBuilder().build(new CacheLoader<Class, List<Field>>() {
            @Override
            public List<Field> load(Class cls) throws Exception {
                List<Field> res = new ArrayList<Field>();
                Class<?> targetClass = cls;

                do {
                    for (final Field field : targetClass.getDeclaredFields())
                        if (field.isAnnotationPresent(annotation))
                            res.add(field);

                    targetClass = targetClass.getSuperclass();
                } while (targetClass != null && targetClass != Object.class);

                //to avoid generation of new objects
                if (res.isEmpty())
                    return Collections.EMPTY_LIST;

                return res;
            }
        });
    }

    public static LoadingCache<Class, List<Method>> buildMethodsCache(final Class<? extends Annotation> annotation) {
        return CacheBuilder.newBuilder().build(new CacheLoader<Class, List<Method>>() {
            @Override
            public List<Method> load(Class cls) throws Exception {
                List<Method> res = new ArrayList<Method>();
                Class<?> targetClass = cls;

                do {
                    for (final Method method : targetClass.getDeclaredMethods()) {
                        if (AnnotationUtils.findAnnotation(method, annotation) != null)
                            res.add(method);
                    }

                    targetClass = targetClass.getSuperclass();
                } while (targetClass != null && targetClass != Object.class);

                //to avoid generation of new objects
                if (res.isEmpty())
                    return Collections.EMPTY_LIST;

                return res;
            }
        });
    }

    private static void doWithFields(Class<?> cls, AccessibleObjectCallback callback) throws Exception {
        List<Field> fields = FIELDS_CACHE.get(cls);
        for (Field field : fields) {
            callback.doWith(field);
        }
    }


    private static void doWithMethods(Class<?> cls, AccessibleObjectCallback callback) throws Exception {
        List<Method> methods = METHODS_CACHE.get(cls);
        for (Method method : methods) {
            callback.doWith(method);
        }
    }


}
/*
 WITHOUT LIMITING THE FOREGOING, COPYING, REPRODUCTION, REDISTRIBUTION,
 REVERSE ENGINEERING, DISASSEMBLY, DECOMPILATION OR MODIFICATION
 OF THE SOFTWARE IS EXPRESSLY PROHIBITED, UNLESS SUCH COPYING,
 REPRODUCTION, REDISTRIBUTION, REVERSE ENGINEERING, DISASSEMBLY,
 DECOMPILATION OR MODIFICATION IS EXPRESSLY PERMITTED BY THE LICENSE
 AGREEMENT WITH NETCRACKER. 
 
 THIS SOFTWARE IS WARRANTED, IF AT ALL, ONLY AS EXPRESSLY PROVIDED IN
 THE TERMS OF THE LICENSE AGREEMENT, EXCEPT AS WARRANTED IN THE
 LICENSE AGREEMENT, NETCRACKER HEREBY DISCLAIMS ALL WARRANTIES AND
 CONDITIONS WITH REGARD TO THE SOFTWARE, WHETHER EXPRESS, IMPLIED
 OR STATUTORY, INCLUDING WITHOUT LIMITATION ALL WARRANTIES AND
 CONDITIONS OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 TITLE AND NON-INFRINGEMENT.
 
 Copyright (c) 1995-2017 NetCracker Technology Corp.
 
 All Rights Reserved.
*/