package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.computerdatabase.exception.SQLUtilsException;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Singleton pattern for connection to the database.
 *
 * @author excilys
 */
@Component("sqlutils")
@Scope("singleton")
public class SQLUtils {

    @Autowired
    @Qualifier("dataSource")
    private HikariDataSource ds;

    /**
     * Constructor of the class.
     */
    public SQLUtils() {
    }

    /**
     * Open a new Connection.
     *
     * @return Connection
     */
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new SQLUtilsException(e);
        }
    }

    public HikariDataSource getDataSource() {
        return ds;
    }

}
