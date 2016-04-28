package com.excilys.computerdatabase.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.service.ServiceComputer;

public interface IValidator<T> {
    static final Logger LOG = LoggerFactory.getLogger(ServiceComputer.class);

    public boolean isValid(T t);

}
