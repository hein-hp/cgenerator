package org.hein.common;

import org.hein.config.ConfigurationFactory;

/**
 * Constant
 */
public final class Constant {

    /**
     * File Name
     */
    public static final String CONFIG_FILE_NAME = "cgenerator-config.yaml";

    /**
     * Common
     */
    public static final String COMMA = ",";
    public static final String DOT = ".";
    public static final String UNDERLINE = "_";

    /**
     * Prefix
     */
    public static final String DATABASE_PREFIX = "cgenerator.database" + DOT;
    public static final String EXTRA_PREFIX = "cgenerator.extra" + DOT;
    public static final String GENERATOR_PREFIX = "cgenerator.generator" + DOT;

    /**
     * Suffix
     */
    public static final String ENTITY_SUFFIX = "DO";
    public static final String MAPPER_SUFFIX = "Mapper";
    public static final String SERVICE_SUFFIX = "Service";
    public static final String SERVICE_IMPL_SUFFIX = "ServiceImpl";
    public static final String CONTROLLER_SUFFIX = "Controller";

    /**
     * Package
     */
    public static final String CONTROLLER_PACKAGE = ConfigurationFactory.getInstance().getAsString(GENERATOR_PREFIX + "packages.controller");
    public static final String ENTITY_PACKAGE = ConfigurationFactory.getInstance().getAsString(GENERATOR_PREFIX + "packages.entity");
    public static final String MAPPER_PACKAGE = ConfigurationFactory.getInstance().getAsString(GENERATOR_PREFIX + "packages.mapper");
    public static final String SERVICE_PACKAGE = ConfigurationFactory.getInstance().getAsString(GENERATOR_PREFIX + "packages.service");
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + DOT + "impl";
}