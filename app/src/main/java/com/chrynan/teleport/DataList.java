package com.chrynan.teleport;

/*
 * Copyright 2016 chRyNaN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by ckeenan on 9/11/16.
 * An annotation to be used on Collection and Array fields to denote each object in the Collection or Array
 * that should be persisted between components with the given keys. Each String in the key array corresponds
 * to its index in the Collection or Array field. If there are more or less keys than indexes in the Collection or
 * Array field then only the keys that have a direct correspondence are mapped.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataList {

    /**
     * Specifies the keys to map the values in the Collection or Array to.
     *
     * @return The String key values.
     */
    String[] value() default "";
}
