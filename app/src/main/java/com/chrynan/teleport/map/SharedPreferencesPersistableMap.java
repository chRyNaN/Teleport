package com.chrynan.teleport.map;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.chrynan.teleport.util.BitmapUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * Created by ckeenan on 8/28/16.
 * A PersistableMap implementation using SharedPreferences as the underlying data structure.
 */
public class SharedPreferencesPersistableMap implements PersistableMap {
    private final static String FILE_NAME = "teleportFileName";

    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public SharedPreferencesPersistableMap(@NonNull Context context, @Nullable Gson gson) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        this.gson = gson == null ? new Gson() : gson;
    }

    @Override
    public void put(String key, Byte value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void put(String key, Short value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void put(String key, Integer value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public void put(String key, Long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    @Override
    public void put(String key, Float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    @Override
    public void put(String key, Double value) {
        sharedPreferences.edit().putString(key, String.valueOf(value)).apply();
    }

    @Override
    public void put(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public void put(String key, Character value) {
        sharedPreferences.edit().putString(key, value.toString()).apply();
    }

    @Override
    public void put(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        BitmapUtil.saveBitmap(context, key, bitmap);
    }

    @Override
    public void put(String key, Serializable value) {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply();
    }

    @Override
    public void put(String key, Parcelable value) {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply();
    }

    @Override
    public <T> void put(String key, T value) {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply();
    }

    @Override
    public <T> void put(String key, Collection<T> value) {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply();
    }

    @Override
    public <T> void put(String key, T[] value) {
        sharedPreferences.edit().putString(key, gson.toJson(value)).apply();
    }

    @Override
    public Byte getByte(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getInt(key, 0) == 0 && sharedPreferences.getInt(key, DefaultValues.BYTE) == DefaultValues.BYTE) {
                    return null;
                }
                return (byte) sharedPreferences.getInt(key, DefaultValues.BYTE);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Short getShort(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getInt(key, 0) == 0 && sharedPreferences.getInt(key, DefaultValues.SHORT) == DefaultValues.SHORT) {
                    return null;
                }
                return (short) sharedPreferences.getInt(key, DefaultValues.SHORT);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer getInteger(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getInt(key, 0) == 0 && sharedPreferences.getInt(key, DefaultValues.INT) == DefaultValues.INT) {
                    return null;
                }
                return sharedPreferences.getInt(key, DefaultValues.INT);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Long getLong(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getLong(key, 0) == 0 && sharedPreferences.getLong(key, DefaultValues.LONG) == DefaultValues.LONG) {
                    return null;
                }
                return sharedPreferences.getLong(key, DefaultValues.LONG);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Float getFloat(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getFloat(key, 0) == 0 && sharedPreferences.getFloat(key, DefaultValues.FLOAT) == DefaultValues.FLOAT) {
                    return null;
                }
                return sharedPreferences.getFloat(key, DefaultValues.FLOAT);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Double getDouble(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getString(key, null) == null &&
                        sharedPreferences.getString(key, String.valueOf(DefaultValues.DOUBLE)).equals(String.valueOf(DefaultValues.DOUBLE))) {
                    return null;
                }
                return Double.valueOf(sharedPreferences.getString(key, null));
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean getBoolean(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getBoolean(key, true) && !sharedPreferences.getBoolean(key, false)) {
                    return null;
                }
                return sharedPreferences.getBoolean(key, false);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Character getCharacter(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getString(key, null) == null &&
                        sharedPreferences.getString(key, String.valueOf(DefaultValues.CHAR)).equals(String.valueOf(DefaultValues.CHAR))) {
                    return null;
                }
                return sharedPreferences.getString(key, String.valueOf(DefaultValues.CHAR)).toCharArray()[0];
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getString(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getString(key, null) == null &&
                        sharedPreferences.getString(key, DefaultValues.STRING).equals(DefaultValues.STRING)) {
                    return null;
                }
                return sharedPreferences.getString(key, DefaultValues.STRING);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bitmap getBitmap(String key) {
        return BitmapUtil.getBitmap(context, key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> T getSerializable(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getString(key, null) == null &&
                        sharedPreferences.getString(key, DefaultValues.STRING).equals(DefaultValues.STRING)) {
                    return null;
                }
                return (T) gson.fromJson(sharedPreferences.getString(key, DefaultValues.STRING), Serializable.class);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Parcelable> T getParcelable(String key) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getString(key, null) == null &&
                        sharedPreferences.getString(key, DefaultValues.STRING).equals(DefaultValues.STRING)) {
                    return null;
                }
                return (T) gson.fromJson(sharedPreferences.getString(key, DefaultValues.STRING), Parcelable.class);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        try {
            if (sharedPreferences.contains(key)) {
                if (sharedPreferences.getString(key, null) == null &&
                        sharedPreferences.getString(key, DefaultValues.STRING).equals(DefaultValues.STRING)) {
                    return null;
                }
                return gson.fromJson(sharedPreferences.getString(key, DefaultValues.STRING), clazz);
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> Collection<T> getCollection(String key, Class<T> clazz) {
        try {
            Type type = new TypeToken<Collection<T>>() {
            }.getType();
            return gson.fromJson(sharedPreferences.getString(key, null), type);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T[] getArray(String key, Class<T> clazz) {
        try {
            Type type = new TypeToken<T[]>() {
            }.getType();
            return gson.fromJson(sharedPreferences.getString(key, null), type);
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }
}
