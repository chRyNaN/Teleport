package com.chrynan.teleport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chrynan.teleport.map.BundlePersistableMap;
import com.chrynan.teleport.map.DelegatePersistableMap;
import com.chrynan.teleport.map.IntentPersistableMap;
import com.chrynan.teleport.map.PersistableMap;
import com.chrynan.teleport.map.SharedPreferencesPersistableMap;
import com.google.gson.Gson;

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
     * Retrieves the underlying {@link PersistableMap} used to store and retrieve data.
     *
     * @return The {@link PersistableMap}.
     */
    public PersistableMap getPersistableMap() {
        return persistableMap;
    }
}