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

package jfeoks.newannot.pojo.update.callback;


import jfeoks.newannot.pojo.nested.*;
import jfeoks.newannot.pojo.update.extractor.PropertyExtractor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.AccessibleObject;

public class ReaderCallback extends AbstractCallback {

    private static ShowPropertyPolicy DEFAULT_SHOW_PROPERTY_POLICY = ShowPropertyPolicy.SHOW;

    private Object instance;
    private MockStartParametersBuilder builder;
    private boolean declareOnly;

    public ReaderCallback(Object instance, MockStartParametersBuilder builder, boolean declareOnly) {
        this.instance = instance;
        this.builder = builder;
        this.declareOnly = declareOnly;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AccessibleObject> void doWith(T accessibleObject) throws Exception {
        String propertyName;
        ShowPropertyPolicy showPropertyPolicy;
        boolean mdcAware = false;

        DFParam dfParam = AnnotationUtils.getAnnotation(accessibleObject, DFParam.class);
        PropertyExtractor propertyExtractor = PropertyExtractorBuilder.createExtractor(accessibleObject.getClass());

        if (dfParam != null) {
            propertyName = StringUtils.defaultIfBlank(dfParam.name(), propertyExtractor.extractName(accessibleObject));
            showPropertyPolicy = dfParam.showPropertyPolicy();
            mdcAware = dfParam.mdcAware();
        } else {
            propertyName = propertyExtractor.extractName(accessibleObject);
            showPropertyPolicy = DEFAULT_SHOW_PROPERTY_POLICY;
        }

        accessibleObject.setAccessible(true);
        Object value = propertyExtractor.extractValue(accessibleObject, instance);

        if (value == null || declareOnly)
            builder.declareVariable(propertyName, mdcAware);
        else {
            try {
                value = convertValue(accessibleObject, value);
            } catch (InstantiationException ie) {
//            ExceptionUtils.<RuntimeException>castAndThrow(ie);
            }

            builder.setVariable(propertyName, value, mdcAware);
        }

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