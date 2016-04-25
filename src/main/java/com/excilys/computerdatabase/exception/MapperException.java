package com.excilys.computerdatabase.exception;

public class MapperException extends RuntimeException {

    /**
     * Exception constructor.
     *
     * @param s
     *            String
     */
    public MapperException(String s) {
        super(s);
    }

    /**
     * Exception constructor.
     *
     * @param e
     *            Exception
     */
    public MapperException(Exception e) {
        super(e);
    }

}
