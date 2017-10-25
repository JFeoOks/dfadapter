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

import java.io.Externalizable;
import java.util.Iterator;
import java.util.Set;

public interface Variables extends Externalizable {

    /**
     * @param name  session property name
     * @param value new session property value, or <code>null</code> if value
     *              should be deleted
     */
    public void setProperty(String name, String value);

    /**
     * @param name  session variable name
     * @param value new session variable value, or <code>null</code> if value
     *              should be deleted
     */
    public void setVariable(String name, Object value);

    public <T> void put(String name, T value);

//    public <T> void putWrapped(String key, Wrapper<T> value);

//    public <T> MutableWrapper<T> convertToMutable(String key);

    public <T> T get(String name);

    /**
     * @param name name of desired property
     * @return property value or <code>null</code> if absent
     * Tries to find value corresponding to given <i>name</i> in the following
     * order: local cache, NC Directory (if available), system properties.
     * Returns <code>null</code> if property can't be found. Once found property
     * is stored in local cache and can be changed by
     */
    public String getProperty(String name);

    public Object getVariable(String name);

//    public <T> Wrapper<T> getWrapped(String key);

    /**
     * @return all property names
     */
    public Set<String> getPropertyNames();

    /**
     * @return all property names
     */
    @Deprecated
    public Set<String> getVariableNames();

    public Iterator<String> getNameIterator();

    <T extends Variables> void mergeTo(T properties);

    <T extends Variables> void mergeTo(T properties, Iterable<String> names);

    void close();

    void remove(String name);

    void clear();

    void setSerializeXmlVariables(boolean serializeXmlVariables);

    boolean getSerializeXmlVariables();
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