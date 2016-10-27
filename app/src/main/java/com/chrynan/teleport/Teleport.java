package com.chrynan.teleport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/*
 * Copyright 2016 chRyNaN
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Created by chRyNaN on 4/20/2016.
 * This class handles binding retrieved data to appropriate fields of a specified binding object.
 */
@SuppressWarnings("unused")
public class Teleport {

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     *
     * @param bindObject The binding object that is searched for Data annotation named fields,
     *                   which these fields are then propagated with corresponding fetched data.
     * @param context    The Context used to store and retrieve data.
     */
    public static void bind(@NonNull Object bindObject, @NonNull Context context) {
        bind(StorageMap.with(context), bindObject);
    }

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     *
     * @param bindObject The binding object that is searched for Data annotation named fields,
     *                   which these fields are then propagated with corresponding fetched data.
     * @param context    The underlying Context used for storing and retrieving data. This needed Context is
     *                   only used for certain scenarios (ex: saving files) but if the Intent parameter is
     *                   null, then it is used as the default storage mechanism (SharedPreferences created by Context).
     * @param intent     The Intent used to store and retrieve data.
     */
    public static void bind(@NonNull Object bindObject, @NonNull Context context, @NonNull Intent intent) {
        bind(StorageMap.with(context, intent), bindObject);
    }

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     *
     * @param bindObject The binding object that is searched for Data annotation named fields,
     *                   which these fields are then propagated with corresponding fetched data.
     * @param context    The underlying Context used for storing and retrieving data. This needed Context is
     *                   only used for certain scenarios (ex: saving files) but if the Intent parameter is
     *                   null, then it is used as the default storage mechanism (SharedPreferences created by Context).
     * @param bundle     The Bundle used to store and retrieve data.
     */
    public static void bind(@NonNull Object bindObject, @NonNull Context context, @NonNull Bundle bundle) {
        bind(StorageMap.with(context, bundle), bindObject);
    }

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     * Uses the Context as both the binding object and the Context. First checks if the Context is an Activity and
     * if it has an Intent (via the getIntent() method), if this Intent is not null it will attempt to use
     * it as the storage mechanism. Otherwise it will use the default storage mechanism using the Context.
     *
     * @param context The Context object that acts as both the binding object and the Context.
     */
    public static void bind(@NonNull Context context) {
        if (context instanceof Activity && ((Activity) context).getIntent() != null) {
            bind(context, context, ((Activity) context).getIntent());
        } else {
            bind(context, context);
        }
    }

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     * Uses the Fragment as both the binding object and to get the Context (via getActivity() method).
     * First checks the Fragment for a Bundle (via the getArguments() method), if this Bundle is not null
     * it will attempt to use it as the storage mechanism. Otherwise it will use the default storage
     * mechanism using the Context.
     *
     * @param fragment The Fragment object that acts both as the binding object and to get the Context.
     */
    public static void bind(@NonNull Fragment fragment) {
        if (fragment.getArguments() != null) {
            bind(fragment, fragment.getActivity(), fragment.getArguments());
        } else {
            bind(fragment, fragment.getActivity());
        }
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param bindObject The binding object to check for Data annotated fields (objects to be stored).
     * @param context    The context used for the underlying storage mechanism; StorageMap.
     */
    public static void beam(@NonNull Object bindObject, @NonNull Context context) {
        beam(StorageMap.with(context), bindObject);
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param bindObject The binding object to check for Data annotated fields (objects to be stored).
     * @param context    The context used for the underlying storage mechanism; StorageMap.
     * @param intent     The intent used with context for the underlying storage mechanism; StorageMap.
     */
    public static void beam(@NonNull Object bindObject, @NonNull Context context, @NonNull Intent intent) {
        beam(StorageMap.with(context, intent), bindObject);
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param bindObject The binding object to check for Data annotated fields (objects to be stored).
     * @param context    The context used for the underlying storage mechanism; StorageMap.
     * @param bundle     The bundle used with context for the underlying storage mechanism; StorageMap.
     */
    public static void beam(@NonNull Object bindObject, @NonNull Context context, @NonNull Bundle bundle) {
        beam(StorageMap.with(context, bundle), bindObject);
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context. Context acts as both the binding object and the underlying storage context.
     *
     * @param context The binding object and the underlying storage object used with StorageMap.
     */
    public static void beam(@NonNull Context context) {
        beam(context, context);
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context. Context acts as both the binding object and the underlying storage context.
     *
     * @param context Context acts as both the binding object and the underlying storage context.
     * @param intent  The intent used with context for the underlying storage mechanism; StorageMap.
     */
    public static void beam(@NonNull Context context, @NonNull Intent intent) {
        beam(context, context, intent);
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param context Context acts as both the binding object and the underlying storage context.
     * @param bundle  The bundle used with context for the underlying storage mechanism; StorageMap.
     */
    public static void beam(@NonNull Context context, @NonNull Bundle bundle) {
        beam(context, context, bundle);
    }

    /**
     * Performs the binding function, as in retrieving the stored values from the provided StorageMap.
     *
     * @param storageMap The StorageMap used to retrieve the data.
     * @param bindObject The Object to get data fields to place values from.
     */
    private static void bind(@NonNull StorageMap storageMap, @NonNull Object bindObject) {

        for (final Field f : getFieldsSpecifiedInDataFieldsAnnotation(bindObject)) {
            try {
                f.set(bindObject, storageMap.get(f.getName(), f.getType()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for (final Field f : getFieldsWithDataAnnotation(bindObject)) {

            final Data d = f.getAnnotation(Data.class);

            try {
                if (d.bind()) {
                    if (d.value().replaceAll("\\s+", "").equals("")) {
                        f.set(bindObject, storageMap.get(f.getName(), f.getType()));
                    } else {
                        f.set(bindObject, storageMap.get(d.value(), f.getType()));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Performs the beaming function, as in storing all the annotated data with the provided StorageMap Object.
     *
     * @param storageMap The StorageMap used to store the data.
     * @param bindObject The Object to get data fields to store from.
     */
    private static void beam(@NonNull StorageMap storageMap, @NonNull Object bindObject) {

        for (final Field f : getFieldsSpecifiedInDataFieldsAnnotation(bindObject)) {
            try {
                storageMap.put(f.getName(), f.get(bindObject));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        for (final Field f : getFieldsWithDataAnnotation(bindObject)) {

            final Data d = f.getAnnotation(Data.class);

            try {

                if (d.beam()) {

                    if (d.value().replaceAll("\\s+", "").equals("")) {
                        storageMap.put(f.getName(), f.get(bindObject));
                    } else {
                        storageMap.put(d.value(), f.get(bindObject));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Retrieves are fields within the provided bind object class that are specified in the {@link DataFields} annotation on that class.
     *
     * @param bindObject The class containing the fields to retrieve.
     * @return A {@link List<Field>} containing fields that are specified in a {@link DataFields#value()} method.
     */
    private static List<Field> getFieldsSpecifiedInDataFieldsAnnotation(Object bindObject) {
        final List<Field> fields = new ArrayList<>();

        final Class<?> bindObjectClass = bindObject.getClass();

        final DataFields dataFields = bindObjectClass.getAnnotation(DataFields.class);

        final String[] dataFieldNames = dataFields.value();

        if (dataFieldNames != null && dataFieldNames.length > 0) {

            // FIXME: O(n^2) is pretty slow, should fix this if possible
            for (final Field field : bindObjectClass.getDeclaredFields()) {

                for (final String dataFieldName : dataFieldNames) {

                    if (field.getName().equals(dataFieldName)) {

                        field.setAccessible(true);

                        fields.add(field);
                    }
                }
            }
        }

        return fields;
    }

    /**
     * Retrieves all fields within the provided bind objects class annotated with the {@link Data} class.
     *
     * @param bindObject The class containing the Fields to retrieve.
     * @return A {@link List<Field>} containing fields annotated with the {@link Data} annotation.
     */
    private static List<Field> getFieldsWithDataAnnotation(Object bindObject) {
        final List<Field> fields = new ArrayList<>();

        for (final Field field : bindObject.getClass().getDeclaredFields()) {

            if (field.isAnnotationPresent(Data.class)) {

                field.setAccessible(true);

                fields.add(field);
            }
        }

        return fields;
    }

}
