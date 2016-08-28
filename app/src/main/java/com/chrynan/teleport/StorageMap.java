package com.chrynan.teleport;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

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
 * Created by chRyNaN on 4/20/2016. This class handles storing and retrieving information from one component to another.
 */
public class StorageMap {
    private static final String TAG = StorageMap.class.getSimpleName();
    private static final String TEMP_FOLDER_NAME = "temp" + File.separator + "images";
    private static final String PREF_KEY = TAG + "_preferences";
    private Context context;
    private Intent intent;
    private Bundle bundle;

    /**
     * Private Constructor instance to prevent this class from being directly Instantiated.
     *
     * @param context The Context used to store and retrieve objects.
     */
    private StorageMap(Context context) {
        this.context = context;
    }

    /**
     * Private Constructor instance to prevent this class from being directly Instantiated.
     *
     * @param context The Context from within this object is created.
     * @param intent  The Intent used to store and retrieve objects.
     */
    private StorageMap(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    /**
     * Private Constructor instance to prevent this class from being directly Instantiated.
     *
     * @param context The Context from within this object is created.
     * @param bundle  The Bundle used to store and retrieve objects.
     */
    private StorageMap(Context context, Bundle bundle) {
        this.context = context;
        this.bundle = bundle;
    }

    /**
     * Creates an instance of this class using the specified Context.
     *
     * @param context The Context used to store and retrieve data.
     * @return StorageMap A new instance of this class.
     */
    public static StorageMap with(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in with method of " + StorageMap.class.getSimpleName() +
                    " class must not be null.");
        }
        return new StorageMap(context);
    }

    /**
     * Creates an instance of this class using the specified Context.
     *
     * @param context The Context from within this object is created.
     * @param intent  The Intent used to store and retrieve data.
     * @return StorageMap A new instance of this class.
     */
    public static StorageMap with(@NonNull Context context, @NonNull Intent intent) {
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in with method of " + StorageMap.class.getSimpleName() +
                    " class must not be null.");
        }
        if (intent == null) {
            throw new IllegalArgumentException("Intent parameter in with method of " + StorageMap.class.getSimpleName() +
                    " class must not be null.");
        }
        return new StorageMap(context, intent);
    }

    /**
     * Creates an instance of this class using the specified Context.
     *
     * @param context The Context from within this object is created.
     * @param bundle  The Bundle used to store and retrieve data.
     * @return StorageMap A new instance of this class.
     */
    public static StorageMap with(@NonNull Context context, @NonNull Bundle bundle) {
        if (context == null) {
            throw new IllegalArgumentException("Context parameter in with method of " + StorageMap.class.getSimpleName() +
                    " class must not be null.");
        }
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle parameter in with method of " + StorageMap.class.getSimpleName() +
                    " class must not be null.");
        }
        return new StorageMap(context, bundle);
    }

    /**
     * Retrieves a stored value with the specified key using the underlying Object (Context, Intent, or Bundle) used
     * when creating an instance of this Object.
     *
     * @param key   The key representing the Object to be fetched.
     * @param clazz The class type this Object is of.
     * @return T The Object retrieved converted to the specified class.
     */
    public <T> T get(String key, Class<T> clazz) {
        T object = null;
        if (clazz.isAssignableFrom(Bitmap.class)) {
            return (T) getBitmap(key);
        }
        if (intent != null) {
            object = getFromIntent(key, clazz);
        }
        if (object == null && bundle != null) {
            object = getFromBundle(key, clazz);
        }
        if (object == null) {
            if (isPreferenceable(object)) {
                object = getPreference(key, clazz);
            } else {
                try {
                    String s = getPreference(key, String.class);
                    if (s != null) {
                        object = new Gson().fromJson(s, clazz);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    /**
     * Stores a value with the specified key using the underlying Object (Context, Intent, or Bundle) used when creating an
     * instance of this Object.
     *
     * @param key    The key representing the Object to be stored.
     * @param object The Object to be stored.
     * @return StorageMap This instance of this class for method chaining.
     */
    public <T> StorageMap put(@NonNull String key, @NonNull T object) {
        if (key == null) {
            throw new IllegalArgumentException("Key parameter in put method of " + StorageMap.class.getSimpleName() +
                    " class must not be null.");
        }
        if (object == null) {
            throw new IllegalArgumentException("Object parameter in put method of " + StorageMap.class.getSimpleName() +
                    " class must not be null.");
        }
        if (object instanceof View) {
            throw new IllegalArgumentException("Object parameter in put method of " + StorageMap.class.getSimpleName() +
                    " class must not be of type View; Views cannot be persisted between different components.");
        }
        if (object instanceof Bitmap) {
            //Bitmaps can be rather large files and should not be passed between components through Intents,
            //Bundles, or SharedPreferences. Instead they should be saved to a file and the File location
            //should be shared to other components.
            saveBitmap(key, (Bitmap) object);
        } else {
            if (intent == null && bundle == null) {
                //When creating an instance of this Object, the user did not specify an Intent or Bundle.
                //Therefore, we can use SharedPreferences, static fields, Files, or SQLite.
                if (isPreferenceable(object)) {
                    savePreference(key, object);
                } else {
                    String json = new Gson().toJson(object);
                    savePreference(key, json);
                }
            } else if (intent != null) {
                //The user specified an Intent when creating an instance of this Object, so use the Intent.
                Bundle b = saveToBundle(key, object);
                intent.putExtras(b);
            } else {
                //The user specified a Bundle when creating an instance of this Object, so use the Bundle.
                saveToBundle(key, object);
            }
        }
        return this;
    }

    /**
     * Determines whether the specified object is of a type that can be saved to SharedPreferences
     *
     * @param <T> object The object to be checked.
     * @return boolean True if the object can be saved to SharedPreferences, false otherwise.
     */
    private <T> boolean isPreferenceable(T object) {
        if (object != null) {
            if (object instanceof Boolean || object instanceof Float ||
                    object instanceof Integer || object instanceof Long ||
                    object instanceof String) {
                return true;
            } else if (object instanceof Set) {
                try {
                    Set<String> stringSet = (Set<String>) object;
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Saves the specified object in the SharedPreferences.
     *
     * @param key    The key used to reference this object.
     * @param object The object to be saved
     */
    private <T> void savePreference(String key, T object) {
        if (context == null) return;
        SharedPreferences.Editor editor =
                context.getSharedPreferences(context.getApplicationContext().getPackageName() + "." + PREF_KEY,
                        Context.MODE_PRIVATE).edit();
        if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Set) {
            editor.putStringSet(key, (Set<String>) object);
        }
        editor.commit();
    }

    /**
     * Very similar to the getPreference(String, Class<T>, T) method but with a default value of null (Meaning
     * each class will return their already defined default values, ex: -1 for numbers). Delegates the logic
     * to the other getPreference() method, refer to that method for more details.
     */
    private <T> T getPreference(String key, Class<T> clazz) {
        return getPreference(key, clazz, null);
    }

    /**
     * Retrieves a value from SharedPreferences using the specified key.
     *
     * @param key          The key representing the value to be retrieved.
     * @param clazz        The class type of the value to be retrieved.
     * @param defaultValue The value to return if the key could not be found.
     * @return T The value represented by the specified key, or the defaultValue if no value is found, or
     * null if the class is not able to be stored in SharedPreferences.
     */
    private <T> T getPreference(String key, Class<T> clazz, T defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(context.getApplicationContext().getPackageName() + "." + PREF_KEY,
                Context.MODE_PRIVATE);
        if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.isAssignableFrom(clazz)) {
            return (T) new Boolean(pref.getBoolean(key, (defaultValue != null) ? (Boolean) defaultValue : false));
        } else if (Integer.class.isAssignableFrom(clazz) || Integer.TYPE.isAssignableFrom(clazz)) {
            return (T) new Integer(pref.getInt(key, (defaultValue != null) ? (Integer) defaultValue : -1));
        } else if (Long.class.isAssignableFrom(clazz) || Long.TYPE.isAssignableFrom(clazz)) {
            return (T) new Long(pref.getLong(key, (defaultValue != null) ? (Long) defaultValue : -1));
        } else if (Float.class.isAssignableFrom(clazz) || Float.TYPE.isAssignableFrom(clazz)) {
            return (T) new Float(pref.getFloat(key, (defaultValue != null) ? (Float) defaultValue : -1));
        } else if (String.class.isAssignableFrom(clazz)) {
            return (T) pref.getString(key, (defaultValue != null) ? (String) defaultValue : null);
        } else if (clazz.isAssignableFrom(Set.class)) {
            try {
                return (T) pref.getStringSet(key, (defaultValue != null) ? (Set<String>) defaultValue : null);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Saves the specified Bitmap to a temporary file.
     *
     * @param key    The key used to represent this Bitmap.
     * @param bitmap The Bitmap to be saved.
     */
    private void saveBitmap(String key, Bitmap bitmap) {
        if (bitmap == null) return;
        if (context == null) return;
        FileOutputStream out = null;
        try {
            File folder = new File(context.getFilesDir(), TEMP_FOLDER_NAME);
            if (!folder.exists()) folder.mkdirs();
            File file = new File(folder, key);
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            if (intent != null) {
                intent.putExtra(key, file.getPath());
            } else if (bundle != null) {
                bundle.putString(key, file.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Retrieves a Bitmap associated with the specified key from a temporary file.
     * Deletes the file when finished retrieving the Bitmap.
     *
     * @param key The key associated with the Bitmap object being retrieved.
     * @return Bitmap The Bitmap to be retrieved.
     */
    private Bitmap getBitmap(String key) {
        if (key == null) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        File folder = new File(context.getFilesDir(), TEMP_FOLDER_NAME);
        if (!folder.exists()) return null;
        File file = new File(folder, key);
        return BitmapFactory.decodeFile(file.getPath(), options);
    }

    /**
     * Saves the specified object to the underlining Bundle object creating one if necessary.
     * Doesn't work for every type.
     *
     * @param key    The key used to reference the object being stored.
     * @param object The object being saved to the Bundle.
     * @return Bundle The bundle the object has been saved to.
     */
    private <T> Bundle saveToBundle(String key, T object) {
        //Need a better way to do this rather than always using instanceof calls throughout the code.
        //With newer virtual machines, the use of the instanceof operator is highly optimized and rather
        //quick but it's not very object-oriented friendly.
        Bundle b = new Bundle();
        if (object instanceof Parcelable) {
            b.putParcelable(key, (Parcelable) object);
        } else if (object instanceof Parcelable[]) {
            b.putParcelableArray(key, (Parcelable[]) object);
        } else if (object instanceof ArrayList<?>) {
            try {
                ArrayList<? extends Parcelable> list = (ArrayList<? extends Parcelable>) object;
                b.putParcelableArrayList(key, list);
            } catch (Exception e) {
                //Assumes that if the ArrayList does not have a parametized type of Parcelable that it
                //must be of type Serializable. This will cause an error if this assumption is false.
                //ArrayList itself is Serializable.
                b.putSerializable(key, (ArrayList<?>) object);
            }
        } else if (object instanceof Collection<?>) {
            //The Collection interface is not Serializable but the ArrayList class is, so, convert the Collection
            //to an ArrayList.
            ArrayList<?> list = new ArrayList<>((Collection<?>) object);
            //Once again, this assumes the parametized type is of Serializable which will cause an error if this
            //assumption is false.
            b.putSerializable(key, list);
        } else if (object instanceof Serializable) {
            b.putSerializable(key, (Serializable) object);
        } else if (object instanceof SparseArray<?>) {
            try {
                b.putSparseParcelableArray(key, (SparseArray<? extends Parcelable>) object);
            } catch (Exception e) {
                Log.e(TAG, "Error parsing SparseArray; make sure parametized type extends Parcelable.", e);
            }
        } else if (object instanceof String) {
            b.putString(key, (String) object);
        } else if (object instanceof String[]) {
            b.putStringArray(key, (String[]) object);
        } else if (object instanceof Byte) {
            b.putByte(key, (Byte) object);
        } else if (object instanceof Character) {
            b.putChar(key, (Character) object);
        } else if (object instanceof CharSequence) {
            b.putCharSequence(key, (CharSequence) object);
        } else if (object instanceof CharSequence) {
            b.putCharSequence(key, (CharSequence) object);
        } else if (object instanceof CharSequence[]) {
            b.putCharSequenceArray(key, (CharSequence[]) object);
        } else if (object instanceof Integer) {
            b.putInt(key, (Integer) object);
        } else if (object instanceof Short) {
            b.putShort(key, (Short) object);
        } else if (object instanceof Long) {
            b.putLong(key, (Long) object);
        } else if (object instanceof Float) {
            b.putFloat(key, (Float) object);
        } else if (object instanceof Double) {
            b.putDouble(key, (Double) object);
        } else {
            //Use the Gson library dependency to convert the object to a JSON formatted String, then store this String
            //into the Bundle.
            String json = new Gson().toJson(object);
            b.putString(key, json);
        }
        if (bundle != null) {
            bundle.putAll(b);
        }
        return b;
    }

    /**
     * Retrieves an object from the underlying Bundle using the specified key.
     *
     * @param key   The key representing the object to be retrieved.
     * @param clazz The class type of the object to be retrieved.
     * @return T The object being retrieved.
     */
    private <T> T getFromBundle(String key, Class<T> clazz) {
        return getFromBundle(key, clazz, bundle);
    }

    /**
     * Retrieves an object from the specified Bundle using the specified key.
     *
     * @param key    The key representing the object to be retrieved.
     * @param clazz  The class type of the object to be retrieved.
     * @param bundle The Bundle to retrieve the object from.
     * @return T The object being retrieved.
     */
    private <T> T getFromBundle(String key, Class<T> clazz, Bundle bundle) {
        if (bundle == null) return null;
        if (Parcelable.class.isAssignableFrom(clazz)) {
            return (T) bundle.getParcelable(key);
        } else if (Parcelable[].class.isAssignableFrom(clazz)) {
            return (T) bundle.getParcelableArray(key);
        } else if (SparseArray.class.isAssignableFrom(clazz)) {
            return (T) bundle.getSparseParcelableArray(key);
        } else if (ArrayList.class.isAssignableFrom(clazz)) {
            //TODO consider parametized type if possible
            ArrayList<? extends Parcelable> list = bundle.getParcelableArrayList(key);
            if (list != null) {
                return (T) list;
            }
            try {
                ArrayList<?> sList = (ArrayList<?>) bundle.getSerializable(key);
                return (T) sList;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Collection.class.isAssignableFrom(clazz)) {
            try {
                ArrayList<?> list = (ArrayList<?>) bundle.getSerializable(key);
                return (T) list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (Serializable.class.isAssignableFrom(clazz)) {
            return (T) bundle.getSerializable(key);
        } else if (Boolean.class.isAssignableFrom(clazz) || Boolean.TYPE.isAssignableFrom(clazz)) {
            return (T) new Boolean(bundle.getBoolean(key));
        } else if (Byte.class.isAssignableFrom(clazz) || Byte.TYPE.isAssignableFrom(clazz)) {
            return (T) new Byte(bundle.getByte(key));
        } else if (Short.class.isAssignableFrom(clazz) || Short.TYPE.isAssignableFrom(clazz)) {
            return (T) new Short(bundle.getShort(key));
        } else if (Integer.class.isAssignableFrom(clazz) || Integer.TYPE.isAssignableFrom(clazz)) {
            return (T) new Integer(bundle.getInt(key));
        } else if (Long.class.isAssignableFrom(clazz) || Long.TYPE.isAssignableFrom(clazz)) {
            return (T) new Long(bundle.getLong(key));
        } else if (Float.class.isAssignableFrom(clazz) || Float.TYPE.isAssignableFrom(clazz)) {
            return (T) new Float(bundle.getFloat(key));
        } else if (Double.class.isAssignableFrom(clazz) || Double.TYPE.isAssignableFrom(clazz)) {
            return (T) new Double(bundle.getDouble(key));
        } else if (String.class.isAssignableFrom(clazz)) {
            return (T) bundle.getString(key);
        } else if (String[].class.isAssignableFrom(clazz)) {
            return (T) bundle.getStringArray(key);
        } else if (Character.class.isAssignableFrom(clazz) || Character.TYPE.isAssignableFrom(clazz)) {
            return (T) new Character(bundle.getChar(key));
        } else if (CharSequence.class.isAssignableFrom(clazz)) {
            return (T) bundle.getCharSequence(key);
        } else if (CharSequence[].class.isAssignableFrom(clazz)) {
            return (T) bundle.getCharSequenceArray(key);
        } else {
            try {
                //This method might not work for parameterized type objects
                String s = bundle.getString(key);
                if (s != null) {
                    return (T) new Gson().fromJson(s, clazz);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Retrieves an object represented by the specified key from the underlying Bundle within the underlying Intent.
     *
     * @param key   The key representing the object to be retrieved.
     * @param clazz The Bundle to retrieve the object from.
     * @return T The object being retrieved.
     */
    private <T> T getFromIntent(String key, Class<T> clazz) {
        if (intent == null) return null;
        return getFromBundle(key, clazz, intent.getExtras());
    }

}