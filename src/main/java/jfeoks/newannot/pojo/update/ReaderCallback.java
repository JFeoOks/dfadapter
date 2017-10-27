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


import jfeoks.newannot.pojo.nested.*;
import jfeoks.newannot.pojo.nested.DataFlowParameterAdapter;
import jfeoks.newannot.pojo.update.annotation.DFBidirectionalParamAdapter;
import jfeoks.newannot.pojo.update.annotation.PropertyValue;
import jfeoks.newannot.pojo.update.config.StaticContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.AccessibleObject;

public class ReaderCallback implements AccessibleObjectCallback {

    private static ShowPropertyPolicy DEFAULT_SHOW_PROPERTY_POLICY = ShowPropertyPolicy.SHOW;

    Object instance;
    MockStartParametersBuilder builder;
    boolean declareOnly;

    public ReaderCallback(Object instance, MockStartParametersBuilder builder, boolean declareOnly) {
        this.instance = instance;
        this.builder = builder;
        this.declareOnly = declareOnly;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AccessibleObject> void doWith(T accessibleObject) throws Exception {
        String propertyName = null;
        ShowPropertyPolicy showPropertyPolicy = null;
        boolean mdcAware = false;

        DFParam annotation = AnnotationUtils.getAnnotation(accessibleObject, DFParam.class);
        PropertyExtractor propertyExtractor = PropertyExtractorBuilder.createExtractor(accessibleObject.getClass());

        if (annotation != null) {
            propertyName = StringUtils.defaultIfBlank(annotation.name(), propertyExtractor.extractName(accessibleObject));
            showPropertyPolicy = annotation.showPropertyPolicy();
            mdcAware = annotation.mdcAware();
        } else {
            propertyName = propertyExtractor.extractName(accessibleObject);
            showPropertyPolicy = DEFAULT_SHOW_PROPERTY_POLICY;
        }

        accessibleObject.setAccessible(true);
        Object value = propertyExtractor.extractValue(accessibleObject, instance);

        try {
            value = convertValue(accessibleObject, value);
        } catch (InstantiationException ie) {
//            ExceptionUtils.<RuntimeException>castAndThrow(ie);
        }
        if (value == null || declareOnly)
            builder.declareVariable(propertyName, mdcAware);
        else
            builder.setVariable(propertyName, value, mdcAware);

        builder.setShowPropertyPolicy(propertyName, showPropertyPolicy);
    }

    @SuppressWarnings("unchecked")
    private <T extends AccessibleObject> Object convertValue(T accessibleObject, Object value) throws IllegalAccessException, InstantiationException {
        if (accessibleObject.isAnnotationPresent(DFParamAdapter.class)) {
            Class<? extends DataFlowParameterAdapter> aClass = accessibleObject.getAnnotation(DFParamAdapter.class).adapterClass();

            DataFlowParameterAdapter converter = aClass.newInstance();
            return converter.convert(value);
        } else if (accessibleObject.isAnnotationPresent(DFBidirectionalParamAdapter.class)) {
            DFBidirectionalParamAdapter adapter = accessibleObject.getAnnotation(DFBidirectionalParamAdapter.class);
            Class<? extends jfeoks.newannot.pojo.update.DataFlowParameterAdapter> aClass = adapter.readAdapterClass();

            jfeoks.newannot.pojo.update.DataFlowParameterAdapter converter = aClass.newInstance();
            ExpressionPropertySource properties = buildPropertySource(adapter.propertyValues());

            return converter.convert(value, properties);
        }

        return value;
    }

    private ExpressionPropertySource buildPropertySource(PropertyValue[] propertyValues) {
        //TODO It's not a good solution
        ExpressionPropertySource expressionPropertySource = StaticContextHolder.getBean(ExpressionPropertySource.class);

        for (PropertyValue value : propertyValues) {
            expressionPropertySource.put(value.name(), value.spelExpression());
        }

        return expressionPropertySource;
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