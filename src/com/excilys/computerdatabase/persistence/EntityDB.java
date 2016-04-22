package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.exception.DAOException;

import java.sql.Connection;

/**
 * Abstract class for DAOs with connection and disconnection method to invoke in each method of the DAO
 * @author excilys
 *
 */
public abstract class EntityDB<T>{

	protected Connection db;
	
	abstract public T find(Long id);
	abstract public List<T> findAll();
	abstract public T update(T c);
	abstract public T create(T c);
	abstract public boolean delete(T c);
	
	/**
	 * Method to connect to database
	 */
	public void connect(){
		db = SQLUtils.getInstance().getConnection();
	}
	
	/**
	 * Method to close a connection to the database
	 */
	public void closeConnection(){
		SQLUtils.close(db);
	}
	
}
