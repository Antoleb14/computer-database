package com.excilys.computerdatabase.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Abstract class for DAOs
 * @author excilys
 *
 */
public abstract class EntityDB {

	protected SQLUtils db;
	protected Statement statement;
	
	/**
	 * Method to connect to database
	 */
	public void connect(){
		try{
			db = SQLUtils.getDbCon();
			statement = db.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
	                ResultSet.CONCUR_READ_ONLY, ResultSet.HOLD_CURSORS_OVER_COMMIT);
		}catch( SQLException e){
			System.out.println("Erreur de connexion à la base de données "+e);
		}
	}
	
	/**
	 * Method to close a connection to the database
	 */
	public void closeConnection(){
		SQLUtils.close();
	}
	
}
