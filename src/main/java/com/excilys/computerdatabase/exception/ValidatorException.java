package com.excilys.computerdatabase.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidatorException extends RuntimeException {

    static final Logger LOG = LoggerFactory.getLogger(ValidatorException.class);

    /**
     * Exception constructor.
     *
     * @param s
     *            String
     */
    public ValidatorException(String s) {
        super(s);
        LOG.error(s);
    }

    /**
     * Exception constructor.
     *
     * @param e
     *            Exception
     */
    public ValidatorException(Exception e) {
        super(e);
        LOG.error(e.getMessage());
    }

}
