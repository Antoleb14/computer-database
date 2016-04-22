package com.excilys.computerdatabase.exception;

public class DAOException extends RuntimeException{

	public DAOException(String s){
		super(s);
	}
	
	public DAOException(Exception e){
		super(e);
	}
	
}
