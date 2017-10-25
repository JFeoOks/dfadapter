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

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.AccessibleObject;

/**
 * Created by egorz on 4/27/2017.
 */
public class WriterCallback implements AccessibleObjectCallback {

    Properties properties;
    Object target;

    public WriterCallback(Properties properties, Object target) {
        this.properties = properties;
        this.target = target;
    }

    @Override
    public <T extends AccessibleObject> void doWith(T accessibleObject) throws Exception {
        DFParam dfParam = accessibleObject.getAnnotation(DFParam.class);
        String propertyName = StringUtils.isNotEmpty(dfParam.name()) ? dfParam.name() : accessibleObject.toString();

        Object variable = properties.getVariable(propertyName);

        if (accessibleObject.isAnnotationPresent(DFParamAdapter.class)) {
            DFParamAdapter dfParamAdapter = accessibleObject.getAnnotation(DFParamAdapter.class);
            Class<? extends DataFlowParameterAdapter> clz = dfParamAdapter.adapterClass();
            DataFlowParameterAdapter adapter = clz.newInstance();
            variable = adapter.convert(variable);
        }

        PropertyPropogatorBuilder.createPropogator(accessibleObject.getClass()).propogate(target, accessibleObject, variable);
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