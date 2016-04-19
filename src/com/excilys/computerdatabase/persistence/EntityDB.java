package com.excilys.computerdatabase.persistence;

import java.sql.SQLException;
import java.sql.Statement;

public abstract class EntityDB {

	protected SQLUtils db;
	protected Statement statement;
	
	public void connect(){
		try{
			db = SQLUtils.getDbCon();
			statement = db.getConnection().createStatement();
		}catch( SQLException e){
			System.out.println("Erreur de connexion à la base de données "+e);
		}
	}
	
	public void closeConnection(){
		SQLUtils.close();
	}
	
}
