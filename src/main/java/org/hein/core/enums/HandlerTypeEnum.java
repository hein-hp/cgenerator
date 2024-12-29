package org.hein.core.enums;

/**
 * Handler Type
 */
public enum HandlerTypeEnum {

    /**
     * Build
     */
    BUILD("build"),

    /**
     * Generate
     */
    GENERATE("generate"),

    /**
     * Null
     */
    NULL("null");

    private final String name;

    HandlerTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}