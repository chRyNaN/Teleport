package com.chrynan.teleport.map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by ckeenan on 8/28/16. A PersistableMap implementation using an Intent as the underlying data structure.
 */
public class IntentPersistableMap implements PersistableMap {
    private final Intent intent;
    private final Gson gson;

    public IntentPersistableMap(@NonNull Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("The Intent parameter in the IntentPersistablemap constructure cannot be null.");
        }
        this.intent = intent;
        this.gson = new Gson();
    }

    @Override
    public void put(String key, Byte value) {
        intent.putExtra(key, (byte) value);
    }

    @Override
    public void put(String key, Short value) {
        intent.putExtra(key, (short) value);
    }

    @Override
    public void put(String key, Integer value) {
        intent.putExtra(key, (int) value);
    }

    @Override
    public void put(String key, Long value) {
        intent.putExtra(key, (long) value);
    }

    @Override
    public void put(String key, Float value) {
        intent.putExtra(key, (float) value);
    }

    @Override
    public void put(String key, Double value) {
        intent.putExtra(key, (double) value);
    }

    @Override
    public void put(String key, Boolean value) {
        intent.putExtra(key, (boolean) value);
    }

    @Override
    public void put(String key, Character value) {
        intent.putExtra(key, (char) value);
    }

    @Override
    public void put(String key, String value) {
        intent.putExtra(key, value);
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        // TODO
    }

    @Override
    public void put(String key, Serializable value) {
        intent.putExtra(key, value);
    }

    @Override
    public void put(String key, Parcelable value) {
        intent.putExtra(key, value);
    }

    @Override
    public <T> void put(String key, T value) {
        intent.putExtra(key, gson.toJson(value));
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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

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
        final Bundle bundle = getBundle();

        return bundle.getString(key);
    }

    @Override
    public Bitmap getBitmap(String key) {
        // TODO
        return null;
    }

    @Override
    public Serializable getSerializable(String key) {
        final Bundle bundle = getBundle();

        return bundle.getSerializable(key);
    }

    @Override
    public Parcelable getParcelable(String key) {
        final Bundle bundle = getBundle();

        return bundle.getParcelable(key);
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        final Bundle bundle = getBundle();

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

    private Bundle getBundle() {
        return intent.getExtras();
    }
}
