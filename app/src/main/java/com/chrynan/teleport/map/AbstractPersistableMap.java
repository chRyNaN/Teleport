package com.chrynan.teleport.map;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by ckeenan on 8/28/16.
 * This class implements the PersistableMap interface and allows classes to extend it without
 * having to provide an implementation for each method. Subclasses could override the methods they are interested in implementing.
 */
@SuppressWarnings("unused")
public class AbstractPersistableMap implements PersistableMap {

    @Override
    public void put(String key, Byte value) {

    }

    @Override
    public void put(String key, Short value) {

    }

    @Override
    public void put(String key, Integer value) {

    }

    @Override
    public void put(String key, Long value) {

    }

    @Override
    public void put(String key, Float value) {

    }

    @Override
    public void put(String key, Double value) {

    }

    @Override
    public void put(String key, Boolean value) {

    }

    @Override
    public void put(String key, Character value) {

    }

    @Override
    public void put(String key, String value) {

    }

    @Override
    public void put(String key, Bitmap bitmap) {

    }

    @Override
    public void put(String key, Serializable value) {

    }

    @Override
    public void put(String key, Parcelable value) {

    }

    @Override
    public <T> void put(String key, T value) {

    }

    @Override
    public <T> void put(String key, Collection<T> value) {

    }

    @Override
    public <T> void put(String key, T[] value) {

    }

    @Override
    public Byte getByte(String key) {
        return null;
    }

    @Override
    public Short getShort(String key) {
        return null;
    }

    @Override
    public Integer getInteger(String key) {
        return null;
    }

    @Override
    public Long getLong(String key) {
        return null;
    }

    @Override
    public Float getFloat(String key) {
        return null;
    }

    @Override
    public Double getDouble(String key) {
        return null;
    }

    @Override
    public Boolean getBoolean(String key) {
        return null;
    }

    @Override
    public Character getCharacter(String key) {
        return null;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public Bitmap getBitmap(String key) {
        return null;
    }

    @Override
    public Serializable getSerializable(String key) {
        return null;
    }

    @Override
    public Parcelable getParcelable(String key) {
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> Collection<T> getCollection(String key, Class<T> clazz) {
        return null;
    }

    @Override
    public <T> T[] getArray(String key, Class<T> clazz) {
        return null;
    }
}
