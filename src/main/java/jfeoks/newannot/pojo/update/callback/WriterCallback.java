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
import jfeoks.newannot.pojo.update.propagator.impl.PropertyPropagatorBuilder;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.AccessibleObject;

public class WriterCallback extends AbstractCallback {

    private Properties properties;
    private Object target;

    public WriterCallback(Properties properties, Object target) {
        this.properties = properties;
        this.target = target;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends AccessibleObject> void doWith(T accessibleObject) throws Exception {
        String propertyName = getPropertyName(accessibleObject);
        Object value = properties.getVariable(propertyName);

        if (value == null) return;

        value = convertValue(accessibleObject, value);
        PropertyPropagatorBuilder.createPropagator(accessibleObject.getClass()).propagate(target, accessibleObject, value);
    }

    @SuppressWarnings("unchecked")
    private <T extends AccessibleObject> String getPropertyName(T accessibleObject) {
        DFParam dfParam = accessibleObject.getAnnotation(DFParam.class);
        PropertyExtractor propertyExtractor = PropertyExtractorBuilder.createExtractor(accessibleObject.getClass());

        String name = null;
        if (dfParam != null) name = dfParam.name();

        return StringUtils.defaultIfEmpty(name, propertyExtractor.extractName(accessibleObject));
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