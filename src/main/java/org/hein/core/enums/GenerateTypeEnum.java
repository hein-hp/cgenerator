package org.hein.core.enums;

/**
 * Generate Type
 */
public enum GenerateTypeEnum {

    /**
     * Mybatis
     */
    MYBATIS("mybatis"),

    /**
     * Mybatis Plus
     */
    MYBATIS_PLUS("mybatis-plus"),

    /**
     * Null
     */
    NULL("null");

    private final String name;

    GenerateTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GenerateTypeEnum of(String name) {
        for (GenerateTypeEnum e : GenerateTypeEnum.values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        throw new IllegalArgumentException("No enum constant " + name);
    }
}