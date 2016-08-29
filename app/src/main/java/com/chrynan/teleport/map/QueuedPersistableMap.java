package com.chrynan.teleport.map;

import android.graphics.Bitmap;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by ckeenan on 8/28/16. A PersistableMap implementation that acts a delegate to other PersistableMaps.
 * This class takes one or more PersistableMap objects in the constructor. When a method is called on this object,
 * it does one of two things dependant on a boolean value specified in a constructor:
 * 1. (default) Attempts to perform the method on each PersistableMap object, in the order they were specified,
 * until the method was successful or there are no more PersistableMap objects.
 * 2. Performs the method on each of the PersistableMap objects.
 */
public class QueuedPersistableMap implements PersistableMap {
    private boolean delegateToAll;

    private List<PersistableMap> persistableMaps;

    public QueuedPersistableMap(PersistableMap... persistableMaps) {
        this.delegateToAll = false;
        this.persistableMaps = Arrays.asList(persistableMaps);
    }

    public QueuedPersistableMap(boolean delegateToAll, PersistableMap... persistableMaps) {
        this.delegateToAll = delegateToAll;
        this.persistableMaps = Arrays.asList(persistableMaps);
    }

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
