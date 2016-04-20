package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton pattern for connection to the database
 * @author excilys
 *
 */
public final class SQLUtils {
	
	private Connection conn=null;
    private static SQLUtils db;
    
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DBNAME = "computer-database-db?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "admincdb";
    private static final String PASS = "qwerty1234";
    
    /**
     * Constructor of the class
     */
    private SQLUtils() {
        try {
            Class.forName(DRIVER).newInstance();
            this.conn = (Connection) DriverManager.getConnection(URL+DBNAME,USER,PASS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to get instance of SQLUtils or create one if null
     * @return SQLUtils Database connection object
     */
    public static synchronized SQLUtils getDbCon() {
        if ( db == null ) {
            db = new SQLUtils();
        }
        return db;
    }
    
    
    /**
     * Close the connection to the database
     */
    public static void close(){
    	try {
    		if(db != null){
    			db.conn.close();
    			db = null;
    		}
		} catch (SQLException e) {
			System.err.println("Error while closing database connection : "+e);
		}
    }

    /**
     * Get the connection object
     * @return Connection
     */
	public Connection getConnection() {
		return this.conn;
	}    

    
}
