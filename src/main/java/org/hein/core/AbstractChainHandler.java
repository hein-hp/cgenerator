package org.hein.core;

/**
 * Abstract Chain Handler
 */
public interface AbstractChainHandler<T> {

    /**
     * handle
     */
    void handle(T data);

    /**
     * mark
     */
    String mark();
}