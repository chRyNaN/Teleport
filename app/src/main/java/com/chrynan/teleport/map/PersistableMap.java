package com.chrynan.teleport.map;

import android.graphics.Bitmap;
import android.os.Parcelable;

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
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Byte value);

    /**
     * Puts a Short into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Short value);

    /**
     * Puts an Integer into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Integer value);

    /**
     * Puts a Long into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Long value);

    /**
     * Puts a Float into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Float value);

    /**
     * Puts a Double into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Double value);

    /**
     * Puts a Boolean into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Boolean value);

    /**
     * Puts a Character into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Character value);

    /**
     * Puts a String into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, String value);

    /**
     * Puts a Bitmap into the underlying persistable data structure or into its own file.
     *
     * @param key    The key used to reference the data.
     * @param bitmap The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Bitmap bitmap);

    /**
     * Puts a Serializable into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Serializable value);

    /**
     * Puts a Parcelable into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @return Whether the object was successfully stored or not.
     */
    boolean put(String key, Parcelable value);

    /**
     * Puts an Object into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @param <T>   The type of object.
     * @return Whether the object was successfully stored or not.
     */
    <T> boolean put(String key, T value);

    /**
     * Puts a Collection into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @param <T>   The type of the objects in the Collection.
     * @return Whether the object was successfully stored or not.
     */
    <T> boolean put(String key, Collection<T> value);

    /**
     * Puts an Array into the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param value The value to store.
     * @param <T>   The type of the objects in the Array.
     * @return Whether the object was successfully stored or not.
     */
    <T> boolean put(String key, T[] value);

    /**
     * Retrieves a Byte from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Byte getByte(String key);

    /**
     * Retrieves a Short from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Short getShort(String key);

    /**
     * Retrieves an Integer from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Integer getInteger(String key);

    /**
     * Retrieves a Long from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Long getLong(String key);

    /**
     * Retrieves a Float from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Float getFloat(String key);

    /**
     * Retrieves a Double from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Double getDouble(String key);

    /**
     * Retrieves a Boolean from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Boolean getBoolean(String key);

    /**
     * Retrieves a Character from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Character getCharacter(String key);

    /**
     * Retrieves a String from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    String getString(String key);

    /**
     * Retrieves a Bitmap from the underlying persistable data structure or from its own file.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Bitmap getBitmap(String key);

    /**
     * Retrieves a Serializable from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Serializable getSerializable(String key);

    /**
     * Retrieves a Parcelable from the underlying persistable data structure.
     *
     * @param key The key used to reference the data.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    Parcelable getParcelable(String key);

    /**
     * Retrieves an Object from the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param clazz The class of the Object.
     * @param <T>   The type of the Object.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    <T> T getObject(String key, Class<T> clazz);

    /**
     * Retrieves a Collection from the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param clazz The class of the Objects in the Collection.
     * @param <T>   The type of the Objects in the Collection.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    <T> Collection<T> getCollection(String key, Class<T> clazz);

    /**
     * Retrieves an Array from the underlying persistable data structure.
     *
     * @param key   The key used to reference the data.
     * @param clazz The class of the Objects in the Array.
     * @param <T>   The type of the Objects in the Array.
     * @return The Object retrieved from the underlying data structure or null if one is not found.
     */
    <T> T[] getArray(String key, Class<T> clazz);
}
