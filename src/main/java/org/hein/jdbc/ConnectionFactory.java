package org.hein.jdbc;

import org.hein.config.ConfigurationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hein.common.Constant.*;
import static org.hein.jdbc.JdbcConstant.*;

/**
 * Connection Factory
 */
public class ConnectionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);

    private final static String DRIVER_NAME = ConfigurationFactory.getInstance().getAsString(DATABASE_PREFIX + JDBC_DRIVER);
    private final static String USERNAME = ConfigurationFactory.getInstance().getAsString(DATABASE_PREFIX + JDBC_USERNAME);
    private final static String PASSWORD = ConfigurationFactory.getInstance().getAsString(DATABASE_PREFIX + JDBC_PASSWORD);
    private final static String URL = ConfigurationFactory.getInstance().getAsString(DATABASE_PREFIX + JDBC_URL);

    static {
        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError();
        }
    }

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("Failed to obtain the database connection.", e);
            throw e;
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Failed to close the database connection", e);
            }
        }
    }
}