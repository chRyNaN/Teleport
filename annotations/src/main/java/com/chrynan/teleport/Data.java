package com.chrynan.teleport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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

/**
 * Created by chRyNaN on 4/20/2016.
 * An annotation to be used on fields that need to be persisted between components.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Data {

    /**
     * The String key used to locate the object in storage. Defaults to an empty String resulting in the
     * object returned to be null.
     *
     * @return String key representing the object in storage.
     */
    String value() default "";

    /**
     * States whether or not to allow sending this object between components with the Teleport.beam() method call.
     * Defaults to true.
     *
     * @return Boolean whether or not to allow sending this object between components.
     */
    boolean beam() default true;

    /**
     * States whether or not to allow retrieving this object between components with the Teleport.bind() method call.
     * Defaults to true.
     *
     * @return Boolean whether or not to allow retrieving this object from storage.
     */
    boolean bind() default true;
}
