package com.chrynan.teleport.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.chrynan.teleport.util.BitmapUtil;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by ckeenan on 8/28/16. A PersistableMap implementation using a Bundle as the underlying data structure.
 */
public class BundlePersistableMap implements PersistableMap {
    private final Bundle bundle;
    private final Gson gson;

    public BundlePersistableMap(@NonNull Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("The Bundle parameter in the BundlePersistablemap constructure cannot be null.");
        }
        this.bundle = bundle;
        this.gson = new Gson();
    }

    @Override
    public void put(String key, Byte value) {
        bundle.putByte(key, value);
    }

    @Override
    public void put(String key, Short value) {
        bundle.putShort(key, value);
    }

    @Override
    public void put(String key, Integer value) {
        bundle.putInt(key, value);
    }

    @Override
    public void put(String key, Long value) {
        bundle.putLong(key, value);
    }

    @Override
    public void put(String key, Float value) {
        bundle.putFloat(key, value);
    }

    @Override
    public void put(String key, Double value) {
        bundle.putDouble(key, value);
    }

    @Override
    public void put(String key, Boolean value) {
        bundle.putBoolean(key, value);
    }

    @Override
    public void put(String key, Character value) {
        bundle.putChar(key, value);
    }

    @Override
    public void put(String key, String value) {
        bundle.putString(key, value);
    }

    @Override
    public void put(Context context, String key, Bitmap bitmap) {
        BitmapUtil.saveBitmap(context, key, bitmap);
    }

    @Override
    public void put(String key, Serializable value) {
        bundle.putSerializable(key, value);
    }

    @Override
    public void put(String key, Parcelable value) {
        bundle.putParcelable(key, value);
    }

    @Override
    public <T> void put(String key, T value) {
        bundle.putString(key, gson.toJson(value));
    }

    @Override
    public <T> void put(String key, Collection<T> value) {
        // TODO
    }

    @Override
    public <T> void put(String key, T[] value) {
        // TODO
    }

    @Override
    public Byte getByte(String key) {
        if (bundle.containsKey(key)) {

            // Check if the value actually exists in the data structure or if zero was returned because it doesn't
            if (bundle.getByte(key) == 0 && bundle.getByte(key, DefaultValues.BYTE) == DefaultValues.BYTE) {
                return null;
            }
            return bundle.getByte(key);
        }
        return null;
    }

    @Override
    public Short getShort(String key) {
        if (bundle.containsKey(key)) {

            if (bundle.getShort(key) == 0 && bundle.getShort(key, DefaultValues.SHORT) == DefaultValues.SHORT) {
                return null;
            }
            return bundle.getShort(key);
        }
        return null;
    }

    @Override
    public Integer getInteger(String key) {
        if (bundle.containsKey(key)) {

            if (bundle.getInt(key) == 0 && bundle.getInt(key, DefaultValues.INT) == DefaultValues.INT) {
                return null;
            }
            return bundle.getInt(key);
        }
        return null;
    }

    @Override
    public Long getLong(String key) {
        if (bundle.containsKey(key)) {

            if (bundle.getLong(key) == 0 && bundle.getLong(key, DefaultValues.LONG) == DefaultValues.LONG) {
                return null;
            }
            return bundle.getLong(key);
        }
        return null;
    }

    @Override
    public Float getFloat(String key) {
        if (bundle.containsKey(key)) {

            if (bundle.getFloat(key) == 0 && bundle.getFloat(key, DefaultValues.FLOAT) == DefaultValues.FLOAT) {
                return null;
            }
            return bundle.getFloat(key);
        }
        return null;
    }

    @Override
    public Double getDouble(String key) {
        if (bundle.containsKey(key)) {

            if (bundle.getDouble(key) == 0 && bundle.getDouble(key, DefaultValues.DOUBLE) == DefaultValues.DOUBLE) {
                return null;
            }
            return bundle.getDouble(key);
        }
        return null;
    }

    @Override
    public Boolean getBoolean(String key) {
        if (bundle.containsKey(key)) {

            if (!bundle.getBoolean(key) && bundle.getBoolean(key, DefaultValues.BOOLEAN)) {
                return null;
            }
            return bundle.getBoolean(key);
        }
        return null;
    }

    @Override
    public Character getCharacter(String key) {
        if (bundle.containsKey(key)) {

            if (bundle.getChar(key) == 0 && bundle.getChar(key, DefaultValues.CHAR) == DefaultValues.CHAR) {
                return null;
            }
            return bundle.getChar(key);
        }
        return null;
    }

    @Override
    public String getString(String key) {
        return bundle.getString(key);
    }

    @Override
    public Bitmap getBitmap(Context context, String key) {
        return BitmapUtil.getBitmap(context, key);
    }

    @Override
    public Serializable getSerializable(String key) {
        return bundle.getSerializable(key);
    }

    @Override
    public Parcelable getParcelable(String key) {
        return bundle.getParcelable(key);
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        return gson.fromJson(bundle.getString(key), clazz);
    }

    @Override
    public <T> Collection<T> getCollection(String key, Class<T> clazz) {
        // TODO
        return null;
    }

    @Override
    public <T> T[] getArray(String key, Class<T> clazz) {
        // TODO
        return null;
    }
}
