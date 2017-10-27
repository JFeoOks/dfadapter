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

package jfeoks.newannot.pojo.nested;


import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class ReaderCallback implements AccessibleObjectCallback {

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
        DFParam dfParam = accessibleObject instanceof Method ? AnnotationUtils.findAnnotation((Method) accessibleObject, DFParam.class) : accessibleObject.getAnnotation(DFParam.class);
        String propertyName = StringUtils.isNotEmpty(dfParam.name()) ? dfParam.name() : accessibleObject.toString();
        ShowPropertyPolicy showPropertyPolicy = dfParam.showPropertyPolicy();

        accessibleObject.setAccessible(true);

        Object value = PropertyExtractorBuilder.createExtractor(accessibleObject.getClass()).extract(accessibleObject, instance);

        try {
            if (accessibleObject.isAnnotationPresent(DFParamAdapter.class)) {
                DFParamAdapter dfParamAdapter = accessibleObject.getAnnotation(DFParamAdapter.class);
                Class<? extends DataFlowParameterAdapter> clz = dfParamAdapter.adapterClass();

                DataFlowParameterAdapter adapter = clz.newInstance();
                //TODO extended type conversion
                value = adapter.convert(value);
            }
        } catch (InstantiationException ie) {
//            ExceptionUtils.<RuntimeException>castAndThrow(ie);
        }
        if (value == null || declareOnly)
            builder.declareVariable(propertyName, dfParam.mdcAware());
        else
            builder.setVariable(propertyName, value, dfParam.mdcAware());

        builder.setShowPropertyPolicy(propertyName, showPropertyPolicy);
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