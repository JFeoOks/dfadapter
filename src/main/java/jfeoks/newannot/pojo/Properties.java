/*
 This software is the confidential information and copyrighted work of
 NetCracker Technology Corp. ("NetCracker") and/or its suppliers and
 is only distributed under the terms of a separate license agreement
 with NetCracker.
 Use of the software is governed by the terms of the license agreement.
 Any use of this software not in accordance with the license agreement
 is expressly prohibited by law, and may result in severe civil
 and criminal penalties. 
 
 Copyright (c) 1995-2015 NetCracker Technology Corp.
 
 All Rights Reserved.
 
*/
package jfeoks.newannot.pojo;


/**
 * Session properties object.
 *
 * @author SEVL0904, SEKI0709
 */
@SuppressWarnings("UnusedDeclaration")
public interface Properties extends Variables {

    /**
     * Check declaration of variable with specified name
     *
     * @param name name of variable
     * @return <b>true</b> if method {@link #declareVariable(String, Object)} was called for this name
     * @since 7.1.3.34
     */
    public boolean isVariableDeclared(String name);

    /**
     * declare variable in this session
     *
     * @param name  name of variable
     * @param value value of variable
     * @since 7.1.3.34
     */
    public void declareVariable(String name, Object value);

    public void declareVariable(String name);

//    public <H extends Properties, T extends Variables> Properties setParentProperties(T properties);

    <T extends Properties> void mergeTo(T properties);

    void closeAll();

    @Deprecated
    public void setProperty(String name, String value, boolean pushUp);

    @Deprecated
    public void setVariable(String name, Object value, boolean pushUp);

    public <T> T getVariable(String name, Class<T> destClass);

    void clearAll();

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
 
 Copyright (c) 1995-2015 NetCracker Technology Corp.
 
 All Rights Reserved.
*/