package com.excilys.computerdatabase.exception;

public class SQLUtilsException extends RuntimeException{

	public SQLUtilsException(String s){
		super(s);
	}
	
	public SQLUtilsException(Exception e){
		super(e);
	}
	
}
