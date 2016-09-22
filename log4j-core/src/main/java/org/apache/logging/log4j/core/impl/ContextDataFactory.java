/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */
package org.apache.logging.log4j.core.impl;

import org.apache.logging.log4j.util.SortedStringArrayMap;
import org.apache.logging.log4j.util.MutableContextData;
import org.apache.logging.log4j.util.PropertiesUtil;

/**
 * Factory for creating MutableContextData instances.
 * <p>
 * By default returns {@code SortedStringArrayMap} objects. Can be configured by setting system property
 * {@code "log4j2.ContextData"} to the fully qualified class name of a class implementing the
 * {@code MutableContextData} interface. The class must have a public default constructor.
 * </p>
 *
 * @see SortedStringArrayMap
 * @since 2.7
 */
public class ContextDataFactory {

    @SuppressWarnings("unchecked")
    public static MutableContextData createContextData() {
        final String CLASS = PropertiesUtil.getProperties().getStringProperty("log4j2.ContextData",
                SortedStringArrayMap.class.getName());
        try {
            return (MutableContextData) Class.forName(CLASS).newInstance();
        } catch (final Exception any) {
            return new SortedStringArrayMap();
        }
    }
}
