package com.chrynan.teleport.map;

import android.os.Parcelable;
import android.support.annotation.Nullable;

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
 * Created by ckeenan on 8/28/16. An interface that represents a Map like structure with methods for storing and retrieving data.
 * This interface can be used as an abstraction for a data structure.
 */
public interface PersistableMap {

    /**
     * Puts a Byte into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Byte value);

    /**
     * Puts a Short into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Short value);

    /**
     * Puts an Integer into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Integer value);

    /**
     * Puts a Long into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Long value);

    /**
     * Puts a Float into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Float value);

    /**
     * Puts a Double into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Double value);

    /**
     * Puts a Boolean into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Boolean value);

    /**
     * Puts a Character into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Character value);

    /**
     * Puts a String into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, String value);

    /**
     * Puts a Serializable into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Serializable value);

    /**
     * Puts a Parcelable into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     */
    void put(String key, Parcelable value);

    /**
     * Puts an Object into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @param <T>   The type of object.
     */
    <T> void put(String key, T value);

    /**
     * Puts a Collection into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @param <T>   The type of the objects in the Collection.
     */
    <T> void put(String key, Collection<T> value);

    /**
     * Puts an Array into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @param <T>   The type of the objects in the Array.
     */
    <T> void put(String key, T[] value);

    /**
     * Retrieves a Byte from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Byte getByte(String key);

    /**
     * Retrieves a Short from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Short getShort(String key);

    /**
     * Retrieves an Integer from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Integer getInteger(String key);

    /**
     * Retrieves a Long from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Long getLong(String key);

    /**
     * Retrieves a Float from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Float getFloat(String key);

    /**
     * Retrieves a Double from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Double getDouble(String key);

    /**
     * Retrieves a Boolean from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Boolean getBoolean(String key);

    /**
     * Retrieves a Character from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    Character getCharacter(String key);

    /**
     * Retrieves a String from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    String getString(String key);

    /**
     * Retrieves a Serializable from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    <T extends Serializable> T getSerializable(String key);

    /**
     * Retrieves a Parcelable from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    <T extends Parcelable> T getParcelable(String key);

    /**
     * Retrieves an Object from the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param clazz The class of the Object.
     * @param <T>   The type of the Object.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    <T> T getObject(String key, Class<T> clazz);

    /**
     * Retrieves a Collection from the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param clazz The class of the Objects in the Collection.
     * @param <T>   The type of the Objects in the Collection.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    <T> Collection<T> getCollection(String key, Class<T> clazz);

    /**
     * Retrieves an Array from the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param clazz The class of the Objects in the Array.
     * @param <T>   The type of the Objects in the Array.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    @Nullable
    <T> T[] getArray(String key, Class<T> clazz);
}
