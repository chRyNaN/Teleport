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
 * An annotation designating fields in the class it is specified on that can be stored and retrieved.
 * This is essentially the same thing as specifying an @Data annotation of every field that was specified.
 * The Strings specified must be the exact name of the fields of the Objects that need to be stored or
 * retrieved otherwise they will be ignored.
 */
@SuppressWarnings("unused")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataFields {

    /**
     * Specifies the fields to be stored and/or retrieved.
     *
     * @return The String names of the fields.
     */
    String[] value() default "";
}
