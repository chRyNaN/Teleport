package com.chrynan.teleport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chrynan.teleport.map.BundlePersistableMap;
import com.chrynan.teleport.map.DelegatePersistableMap;
import com.chrynan.teleport.map.IntentPersistableMap;
import com.chrynan.teleport.map.PersistableMap;
import com.chrynan.teleport.map.SharedPreferencesPersistableMap;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Collection;

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
 * This class handles storing and retrieving information from one component to another.
 */
public class StorageMap {
    private final PersistableMap persistableMap;

    /**
     * Private Constructor instance to prevent this class from being directly Instantiated.
     *
     * @param persistableMap The map used to store values.
     */
    private StorageMap(PersistableMap persistableMap) {
        this.persistableMap = persistableMap;
    }

    /**
     * Creates an instance of this class using the specified Context.
     *
     * @param context The Context used to store and retrieve data.
     * @param gson    The {@link Gson} object for serialization and deserialization.
     * @return StorageMap A new instance of this class.
     */
    public static StorageMap with(@NonNull Context context, @Nullable Gson gson) {

        return new StorageMap(new SharedPreferencesPersistableMap(context, gson));
    }

    /**
     * Creates an instance of this class using the specified Context.
     *
     * @param context The Context from within this object is created.
     * @param intent  The Intent used to store and retrieve data.
     * @param gson    The {@link Gson} object for serialization and deserialization.
     * @return StorageMap A new instance of this class.
     */
    public static StorageMap with(@NonNull Context context, @NonNull Intent intent, @Nullable Gson gson) {

        return new StorageMap(new DelegatePersistableMap(new IntentPersistableMap(context, intent, gson), new SharedPreferencesPersistableMap(context, gson)));
    }

    /**
     * Creates an instance of this class using the specified Context.
     *
     * @param context The Context from within this object is created.
     * @param bundle  The Bundle used to store and retrieve data.
     * @return StorageMap A new instance of this class.
     */
    public static StorageMap with(@NonNull Context context, @NonNull Bundle bundle, @Nullable Gson gson) {

        return new StorageMap(new DelegatePersistableMap(new BundlePersistableMap(context, bundle, gson), new SharedPreferencesPersistableMap(context, gson)));
    }

    /**
     * Retrieves a stored value with the specified key using the underlying Object (Context, Intent, or Bundle) used
     * when creating an instance of this Object.
     *
     * @param key   The key representing the Object to be fetched.
     * @param clazz The class type this Object is of.
     * @return T The Object retrieved converted to the specified class.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(@NonNull String key, Class<T> clazz) {
        if (Byte.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getByte(key);
        } else if (Short.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getShort(key);
        } else if (Integer.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getInteger(key);
        } else if (Long.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getLong(key);
        } else if (Float.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getFloat(key);
        } else if (Double.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getDouble(key);
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getBoolean(key);
        } else if (String.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getString(key);
        } else if (Character.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getCharacter(key);
        } else if (Bitmap.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getBitmap(key);
        } else if (Serializable.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getSerializable(key);
        } else if (Parcelable.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getParcelable(key);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            return (T) persistableMap.getCollection(key, clazz);
        } else if (clazz.isArray()) {
            return (T) persistableMap.getArray(key, clazz);
        } else {
            return persistableMap.getObject(key, clazz);
        }
    }

    /**
     * Stores a value with the specified key using the underlying Object (Context, Intent, or Bundle) used when creating an
     * instance of this Object.
     *
     * @param key    The key representing the Object to be stored.
     * @param object The Object to be stored.
     * @return StorageMap This instance of this class for method chaining.
     */
    public <T> StorageMap put(@NonNull String key, T object) {
        persistableMap.put(key, object);
        return this;
    }
}