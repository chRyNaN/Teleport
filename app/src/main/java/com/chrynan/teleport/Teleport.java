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
 * Created by chRyNaN on 4/20/2016. This class handles binding retrieved data to appropriate fields of a specified binding object.
 */
public class Teleport {
    private static final String TAG = Teleport.class.getSimpleName();

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     *
     * @param bindObject The binding object that is searched for Data annotation named fields,
     *                   which these fields are then propagated with corresponding fetched data.
     * @param context    The Context used to store and retrieve data.
     */
    public static final void bind(@NonNull Object bindObject, @NonNull Context context) {
        if (bindObject == null) {
            throw new IllegalArgumentException("Object parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        StorageMap map = StorageMap.with(context);
        for (Field f : getAnnotatedFields(bindObject)) {
            try {
                f.setAccessible(true);
                Data d = f.getAnnotation(Data.class);
                if (d.bind()) {
                    f.set(bindObject, map.get((d == null) ? "" : d.value(), f.getType()));
                }
            } catch (IllegalAccessException iae) {
                iae.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public static final void bind(@NonNull Object bindObject, @NonNull Context context, Intent intent) {
        if (bindObject == null) {
            throw new IllegalArgumentException("Object parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (intent == null) {
            bind(bindObject, context);
        } else {
            StorageMap map = StorageMap.with(context, intent);
            for (Field f : getAnnotatedFields(bindObject)) {
                try {
                    f.setAccessible(true);
                    Data d = f.getAnnotation(Data.class);
                    if (d.bind()) {
                        f.set(bindObject, map.get((d == null) ? "" : d.value(), f.getType()));
                    }
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
    public static final void bind(@NonNull Object bindObject, @NonNull Context context, Bundle bundle) {
        if (bindObject == null) {
            throw new IllegalArgumentException("Object parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (bundle == null) {
            bind(bindObject, context);
        } else {
            StorageMap map = StorageMap.with(context, bundle);
            for (Field f : getAnnotatedFields(bindObject)) {
                try {
                    f.setAccessible(true);
                    Data d = f.getAnnotation(Data.class);
                    if (d.bind()) {
                        f.set(bindObject, map.get((d == null) ? "" : d.value(), f.getType()));
                    }
                } catch (IllegalAccessException iae) {
                    iae.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     * Uses the Activity as both the binding object and the Context. First checks the Activity for
     * an Intent (via the getIntent() method), if this Intent is not null it will attempt to use
     * it as the storage mechanism. Otherwise it will use the default storage mechanism using the Context.
     *
     * @param activity The Activity object that acts as both the binding object and the Context.
     */
    public static final void bind(@NonNull Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (activity.getIntent() != null) {
            bind(activity, activity, activity.getIntent());
        } else {
            bind(activity, activity);
        }
    }

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     * Uses the Activity as both the binding object and the Context. First attempts to use the provided
     * Intent object as the storage mechanism.
     *
     * @param activity The Activity object that acts as both the binding object and the Context.
     * @param intent   The Intent used to store and retieve data.
     */
    public static final void bind(@NonNull Activity activity, Intent intent) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (intent != null) {
            bind(activity, activity, intent);
        } else {
            bind(activity, activity);
        }
    }

    /**
     * Retrieves and stores data into their corresponding fields within the specified binding object.
     * Uses the Activity as both the binding object and the Context. First attempts to use the provided
     * Bundle object as the storage mechanism.
     *
     * @param activity The Activity object that acts as both the binding object and the Context.
     * @param bundle   The Intent used to store and retieve data.
     */
    public static final void bind(@NonNull Activity activity, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (bundle != null) {
            bind(activity, activity, bundle);
        } else {
            bind(activity, activity);
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
    public static final void bind(@NonNull Fragment fragment) {
        if (fragment == null) {
            throw new IllegalArgumentException("Fragment parameter in bind method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (fragment.getArguments() != null) {
            bind(fragment, fragment.getActivity(), fragment.getArguments());
        } else {
            bind(fragment, fragment.getActivity());
        }
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context. Context acts as both the binding object and the underlying storage context.
     *
     * @param context The binding object and the underlying storage object used with StorageMap.
     */
    public static final void beam(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        beam(context, context);
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param bindObject The binding object to check for Data annotated fields (objects to be stored).
     * @param context    The context used for the underlying storage mechanism; StorageMap.
     */
    public static final void beam(@NonNull Object bindObject, @NonNull Context context) {
        if (bindObject == null) {
            throw new IllegalArgumentException("Object parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        StorageMap map = StorageMap.with(context);
        for (Field f : getAnnotatedFields(bindObject)) {
            try {
                f.setAccessible(true);
                Data d = f.getAnnotation(Data.class);
                if (d.beam()) {
                    map.put(d.value(), f.get(bindObject));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param bindObject The binding object to check for Data annotated fields (objects to be stored).
     * @param context    The context used for the underlying storage mechanism; StorageMap.
     * @param intent     The intent used with context for the underlying storage mechanism; StorageMap.
     */
    public static final void beam(@NonNull Object bindObject, @NonNull Context context, Intent intent) {
        if (bindObject == null) {
            throw new IllegalArgumentException("Object parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (intent == null) {
            beam(bindObject, context);
        } else {
            StorageMap map = StorageMap.with(context, intent);
            for (Field f : getAnnotatedFields(bindObject)) {
                try {
                    f.setAccessible(true);
                    Data d = f.getAnnotation(Data.class);
                    if (d.beam()) {
                        map.put(d.value(), f.get(bindObject));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context. Context acts as both the binding object and the underlying storage context.
     *
     * @param context Context acts as both the binding object and the underlying storage context.
     * @param intent  The intent used with context for the underlying storage mechanism; StorageMap.
     */
    public static final void beam(@NonNull Context context, Intent intent) {
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (intent == null) {
            beam(context, context);
        } else {
            beam(context, context, intent);
        }
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param bindObject The binding object to check for Data annotated fields (objects to be stored).
     * @param context    The context used for the underlying storage mechanism; StorageMap.
     * @param bundle     The bundle used with context for the underlying storage mechanism; StorageMap.
     */
    public static final void beam(@NonNull Object bindObject, @NonNull Context context, Bundle bundle) {
        if (bindObject == null) {
            throw new IllegalArgumentException("Object parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (bundle == null) {
            beam(bindObject, context);
        } else {
            StorageMap map = StorageMap.with(context, bundle);
            for (Field f : getAnnotatedFields(bindObject)) {
                try {
                    f.setAccessible(true);
                    Data d = f.getAnnotation(Data.class);
                    if (d.beam()) {
                        map.put(d.value(), f.get(bindObject));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Stores all fields in the binding object annotated with Data (with beam set to true) using the specified
     * context.
     *
     * @param context Context acts as both the binding object and the underlying storage context.
     * @param bundle  The bundle used with context for the underlying storage mechanism; StorageMap.
     */
    public static final void beam(@NonNull Context context, Bundle bundle) {
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in beam method of " + Teleport.class.getSimpleName() +
                    " class must not be null.");
        }
        if (bundle == null) {
            beam(context, context);
        } else {
            beam(context, context, bundle);
        }
    }

    /**
     * Retrieves all the Field objects that have a Data annotation within the binding object class.
     *
     * @param bindingObject The object to search for Data annotated Fields.
     * @return List<Field> The list of annotated Fields; returns an empty list if there are no Fields.
     */
    private static final List<Field> getAnnotatedFields(Object bindingObject) {
        List<Field> fields = new ArrayList<>();
        for (Field f : bindingObject.getClass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Data.class)) {
                f.setAccessible(true);
                fields.add(f);
            }
        }
        return fields;
    }

}
