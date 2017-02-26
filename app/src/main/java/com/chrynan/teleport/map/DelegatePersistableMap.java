package com.chrynan.teleport.map;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by ckeenan on 8/28/16. A PersistableMap implementation that acts a delegate to other PersistableMaps.
 * This class takes one or more PersistableMap objects in the constructor. When a method is called on this object,
 * it calls the same method in all the PersistableMaps it contains. Put methods will be called on all the PersistableMaps,
 * while a Get method call will return the first non-null value retrieved or null otherwise.
 */
public class DelegatePersistableMap implements PersistableMap {

    private List<PersistableMap> persistableMaps;

    public DelegatePersistableMap(PersistableMap... persistableMaps) {
        this.persistableMaps = Arrays.asList(persistableMaps);
    }

    @Override
    public void put(String key, Byte value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Short value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Integer value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Long value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Float value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Double value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Boolean value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Character value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, String value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Serializable value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public void put(String key, Parcelable value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public <T> void put(String key, T value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public <T> void put(String key, Collection<T> value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public <T> void put(String key, T[] value) {
        for (PersistableMap map : persistableMaps) {
            map.put(key, value);
        }
    }

    @Override
    public Byte getByte(String key) {
        Byte b;
        for (PersistableMap p : persistableMaps) {
            b = p.getByte(key);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    @Override
    public Short getShort(String key) {
        Short s;
        for (PersistableMap p : persistableMaps) {
            s = p.getShort(key);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Integer getInteger(String key) {
        Integer i;
        for (PersistableMap p : persistableMaps) {
            i = p.getInteger(key);
            if (i != null) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Long getLong(String key) {
        Long l;
        for (PersistableMap p : persistableMaps) {
            l = p.getLong(key);
            if (l != null) {
                return l;
            }
        }
        return null;
    }

    @Override
    public Float getFloat(String key) {
        Float f;
        for (PersistableMap p : persistableMaps) {
            f = p.getFloat(key);
            if (f != null) {
                return f;
            }
        }
        return null;
    }

    @Override
    public Double getDouble(String key) {
        Double d;
        for (PersistableMap p : persistableMaps) {
            d = p.getDouble(key);
            if (d != null) {
                return d;
            }
        }
        return null;
    }

    @Override
    public Boolean getBoolean(String key) {
        Boolean b;
        for (PersistableMap p : persistableMaps) {
            b = p.getBoolean(key);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    @Override
    public Character getCharacter(String key) {
        Character c;
        for (PersistableMap p : persistableMaps) {
            c = p.getCharacter(key);
            if (c != null) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String getString(String key) {
        String s;
        for (PersistableMap p : persistableMaps) {
            s = p.getString(key);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Serializable> T getSerializable(String key) {
        Serializable s;
        for (PersistableMap p : persistableMaps) {
            s = p.getSerializable(key);
            if (s != null) {
                return (T) s;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Parcelable> T getParcelable(String key) {
        Parcelable p;
        for (PersistableMap pm : persistableMaps) {
            p = pm.getParcelable(key);
            if (p != null) {
                return (T) p;
            }
        }
        return null;
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {
        T t;
        for (PersistableMap p : persistableMaps) {
            t = p.getObject(key, clazz);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    @Override
    public <T> Collection<T> getCollection(String key, Class<T> clazz) {
        Collection<T> c;
        for (PersistableMap p : persistableMaps) {
            c = p.getCollection(key, clazz);
            if (c != null) {
                return c;
            }
        }
        return null;
    }

    @Override
    public <T> T[] getArray(String key, Class<T> clazz) {
        T[] a;
        for (PersistableMap p : persistableMaps) {
            a = p.getArray(key, clazz);
            if (a != null) {
                return a;
            }
        }
        return null;
    }
}
