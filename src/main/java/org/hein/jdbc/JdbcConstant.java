package org.hein.jdbc;

import java.util.Map;

/**
 * Jdbc Constant
 */
public class JdbcConstant {

    /**
     * Connection
     */
    public static final String JDBC_DRIVER = "jdbc.driver";
    public static final String JDBC_URL = "jdbc.url";
    public static final String JDBC_USERNAME = "jdbc.username";
    public static final String JDBC_PASSWORD = "jdbc.password";

    /**
     * Sql To Java
     */
    public static Map<String, String> SQL_TO_JAVA_TYPE = Map.ofEntries(
            Map.entry("varchar", "String"),
            Map.entry("char", "String"),
            Map.entry("text", "String"),
            Map.entry("longtext", "String"),
            Map.entry("mediumtext", "String"),
            Map.entry("tinytext", "String"),
            Map.entry("datetime", "Date"),
            Map.entry("date", "Date"),
            Map.entry("time", "Date"),
            Map.entry("timestamp", "Date"),
            Map.entry("int", "Integer"),
            Map.entry("tinyint", "Integer"),
            Map.entry("smallint", "Integer"),
            Map.entry("mediumint", "Integer"),
            Map.entry("bigint", "Long"),
            Map.entry("decimal", "BigDecimal"),
            Map.entry("float", "Float"),
            Map.entry("double", "Double"),
            Map.entry("bit", "Boolean"),
            Map.entry("tinyblob", "byte[]"),
            Map.entry("blob", "byte[]"),
            Map.entry("mediumblob", "byte[]"),
            Map.entry("longblob", "byte[]")
    );
}