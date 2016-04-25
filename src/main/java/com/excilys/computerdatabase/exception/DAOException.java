package com.excilys.computerdatabase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOException extends RuntimeException {

    static final Logger LOG = LoggerFactory.getLogger(DAOException.class);

    /**
     * Exception constructor.
     *
     * @param s
     *            String
     */
    public DAOException(String s) {
        super(s);
        LOG.error(s);
    }

    /**
     * Exception constructor.
     *
     * @param e
     *            Exception
     */
    public DAOException(Exception e) {
        super(e);
        LOG.error(e.getMessage());
    }

}
