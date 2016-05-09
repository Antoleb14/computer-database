package com.excilys.computerdatabase.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.computerdatabase.exception.SQLUtilsException;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Singleton pattern for connection to the database.
 *
 * @author excilys
 */
public enum SQLUtils {

    INSTANCE;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static HikariDataSource ds;
    public static ThreadLocal<Connection> tlocal = new ThreadLocal<Connection>();

    static {
        // HikariConfig config = new
        // HikariConfig("/resources/hikari.properties");
        // ds = new HikariDataSource(config);
        // try {
        // Class.forName(DRIVER).newInstance();
        // } catch (Exception e) {
        // throw new SQLUtilsException(e);
        // }

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new SQLUtilsException("Where is your MySQL JDBC Driver : " + e.getMessage());
        }
        Properties properties = new Properties();
        InputStream propertiesFile = SQLUtils.class.getClassLoader().getResourceAsStream("hikari.properties");
        try {
            properties.load(propertiesFile);
        } catch (IOException e) {
            throw new SQLUtilsException("Cannot get MySQL connection : " + e.getMessage());
        }

        String dbname = properties.getProperty("URL") + properties.getProperty("DBNAME");
        String username = properties.getProperty("USER");
        String password = properties.getProperty("PASS");

        ds = new HikariDataSource();
        ds.setJdbcUrl(dbname);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMaximumPoolSize(50);

    }

    /**
     * Open a new Connection.
     *
     * @return Connection
     */
    public Connection getConnection() {
        try {
            if (tlocal.get() == null) {
                tlocal.set(ds.getConnection());
            }
        } catch (SQLException e) {
            throw new SQLUtilsException(e);
        }
        return tlocal.get();

    }

    /**
     * Close the connection to the database.
     *
     * @param c
     *            Connection
     */
    public void close() {
        try {
            if (tlocal.get() != null) {
                tlocal.get().close();
            }
            tlocal.remove();
        } catch (SQLException e) {
            throw new SQLUtilsException(e);
        }
    }

    public void commit() {
        try {
            tlocal.get().commit();
        } catch (SQLException e) {
            throw new SQLUtilsException(e);
        }
    }

    public void setAutoCommit(boolean val) {
        try {
            tlocal.get().setAutoCommit(val);
        } catch (SQLException e) {
            throw new SQLUtilsException(e);
        }
    }

    public void rollback() {
        try {
            tlocal.get().rollback();
        } catch (SQLException e) {
            throw new SQLUtilsException(e);
        }
    }

}
