package com.chrynan.teleport.map;

/**
 * Created by ckeenan on 9/11/16. This class contains static fields of values to be used in Persistable Map classes as defaults
 * for checking if the fields exist in the underlying data structure.
 */
class DefaultValues {

    static final byte BYTE = Byte.MIN_VALUE;
    static final short SHORT = Short.MIN_VALUE;
    static final int INT = Integer.MIN_VALUE;
    static final long LONG = Long.MIN_VALUE;
    static final float FLOAT = Float.MIN_VALUE;
    static final double DOUBLE = Double.MIN_VALUE;
    static final boolean BOOLEAN = true;
    static final char CHAR = Character.MIN_VALUE;
    static final String STRING = "string";
}
