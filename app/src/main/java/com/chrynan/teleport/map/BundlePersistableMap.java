package com.chrynan.teleport.map;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by ckeenan on 8/28/16. A PersistableMap implementation using a Bundle as the underlying data structure.
 */
public class BundlePersistableMap implements PersistableMap {
    @Override
    public boolean put(String key, Byte value) {
        return false;
    }

    @Override
    public boolean put(String key, Short value) {
        return false;
    }

    @Override
    public boolean put(String key, Integer value) {
        return false;
    }

    @Override
    public boolean put(String key, Long value) {
        return false;
    }

    @Override
    public boolean put(String key, Float value) {
        return false;
    }

    @Override
    public boolean put(String key, Double value) {
        return false;
    }

    @Override
    public boolean put(String key, Boolean value) {
        return false;
    }

    @Override
    public boolean put(String key, Character value) {
        return false;
    }

    @Override
    public boolean put(String key, String value) {
        return false;
    }

    @Override
    public boolean put(String key, Bitmap bitmap) {
        return false;
    }

    @Override
    public boolean put(String key, Serializable value) {
        return false;
    }

    @Override
    public boolean put(String key, Parcelable value) {
        return false;
    }

    @Override
    public <T> boolean put(String key, T value) {
        return false;
    }

    @Override
    public <T> boolean put(String key, Collection<T> value) {
        return false;
    }

    @Override
    public <T> boolean put(String key, T[] value) {
        return false;
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
