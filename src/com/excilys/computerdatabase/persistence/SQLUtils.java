package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.excilys.computerdatabase.exception.SQLUtilsException;

/**
 * Singleton pattern for connection to the database
 * 
 * @author excilys
 *
 */
public final class SQLUtils {

	private static SQLUtils db;

	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String DBNAME = "computer-database-db?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String USER = "admincdb";
	private static final String PASS = "qwerty1234";

	static {
		try {
			Class.forName(DRIVER).newInstance();
		} catch (Exception e) {
			throw new SQLUtilsException(e);
		}
	}

	/**
	 * Constructor of the class
	 */
	private SQLUtils() {
	}

	/**
	 * Method to get instance of SQLUtils or create one if null
	 * 
	 * @return SQLUtils Database connection object
	 */
	public static SQLUtils getInstance() {
		if (db == null) {
			synchronized (SQLUtils.class) {
				if (db == null) {
					db = new SQLUtils();
				}
			}
		}
		return db;
	}

	/**
	 * Open a new Connection
	 * @return Connection
	 */
	public Connection getConnection() {
		Connection co = null;
		synchronized (this) {
			try {
				co = DriverManager.getConnection(URL + DBNAME, USER, PASS);
			} catch (SQLException e) {
				throw new SQLUtilsException(e);
			}
		}
		return co;
	}

	/**
	 * Close the connection to the database
	 */
	public static void close(Connection c) {
		synchronized (SQLUtils.class) {
			try {
				c.close();
			} catch (SQLException e) {
				throw new SQLUtilsException(e);
			}
		}
	}

}
