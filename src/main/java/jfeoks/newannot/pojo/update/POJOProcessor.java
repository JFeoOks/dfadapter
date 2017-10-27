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
package jfeoks.newannot.pojo.update;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jfeoks.newannot.pojo.nested.*;
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

    public static void process(Request object) throws Exception {
        process(object, object);
    }

    public static MockSessionHandler process(Request request, Object response) throws Exception {
        performValidation(request);
        MockStartParametersBuilder builder = MockStartParametersBuilder.newInstance();
        builder.setAsynchronous(request.isAsynchronous());
//            builder.setMDCContainer(mdcContainer);
        StartParametersProvider startParametersProvider = new StartParametersProvider(request, response,  builder);
        MockStartParameters startParameters = startParametersProvider.buildStartParameters();
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
            throw new ConstraintViolationException("Incorrect request data passed to DataFlow POJOProcessor: " + sets, sets);
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

    private static void doWithFields(Class<?> cls, AccessibleObjectCallback callback) throws Exception {
        List<Field> fields = FIELDS_CACHE.get(cls);
        for (Field field : fields) {
            callback.doWith(field);
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