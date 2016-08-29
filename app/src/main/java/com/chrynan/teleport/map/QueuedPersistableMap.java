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
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Short value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Integer value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Long value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Float value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Double value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Boolean value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Character value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, String value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Bitmap bitmap) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, bitmap);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Serializable value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public boolean put(String key, Parcelable value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public <T> boolean put(String key, T value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public <T> boolean put(String key, Collection<T> value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
    }

    @Override
    public <T> boolean put(String key, T[] value) {
        boolean successful = false;
        for (PersistableMap p : persistableMaps) {
            boolean b = p.put(key, value);
            if (!delegateToAll && b) {
                return true;
            } else {
                successful = b ? true : successful;
            }
        }
        return successful;
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

    @Override
    public Bitmap getBitmap(String key) {
        Bitmap b;
        for (PersistableMap p : persistableMaps) {
            b = p.getBitmap(key);
            if (b != null) {
                return b;
            }
        }
        return null;
    }

    @Override
    public Serializable getSerializable(String key) {
        Serializable s;
        for (PersistableMap p : persistableMaps) {
            s = p.getSerializable(key);
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    @Override
    public Parcelable getParcelable(String key) {
        Parcelable p;
        for (PersistableMap pm : persistableMaps) {
            p = pm.getParcelable(key);
            if (p != null) {
                return p;
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
