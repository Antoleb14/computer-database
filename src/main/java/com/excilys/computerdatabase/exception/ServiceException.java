package com.excilys.computerdatabase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends RuntimeException {

    static final Logger LOG = LoggerFactory.getLogger(DAOException.class);

    /**
     * Exception constructor.
     *
     * @param s
     *            String
     */
    public ServiceException(String s) {
        super(s);
        LOG.error(s);
    }

    /**
     * Exception constructor.
     *
     * @param e
     *            Exception
     */
    public ServiceException(Exception e) {
        super(e);
        LOG.error(e.getMessage());
    }

}
