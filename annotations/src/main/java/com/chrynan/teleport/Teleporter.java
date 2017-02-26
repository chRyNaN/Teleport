package com.chrynan.teleport;

/**
 * Created by ckeenan on 2/26/17. An interface for an object that can persist and retrieve values
 * for a class containing fields annotated with the {@link Data} annotation.
 */
public interface Teleporter {
    /**
     * Store the values.
     */
    void beam();

    /**
     * Retrieve the values.
     */
    void bind();
}
