package com.excilys.computerdatabase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLUtilsException extends RuntimeException {

    static final Logger LOG = LoggerFactory.getLogger(DAOException.class);

    /**
     * Exception constructor.
     *
     * @param s
     *            String
     */
    public SQLUtilsException(String s) {
        super(s);
        LOG.error(s);
    }

    /**
     * Exception constructor.
     *
     * @param e
     *            Exception
     */
    public SQLUtilsException(Exception e) {
        super(e);
        LOG.error(e.getMessage());
    }

}
