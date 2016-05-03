package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.persistence.ComputerDB;
import com.excilys.computerdatabase.validator.ValidatorComputer;

public enum ServiceComputer implements IService<Computer> {

    INSTANCE;

    static final Logger LOG = LoggerFactory.getLogger(ServiceComputer.class);
    private final ComputerDB CDB = ComputerDB.INSTANCE;

    @Override
    public Computer find(Long id) throws ServiceException {
        LOG.debug("find a Computer id : " + id);
        Computer c = null;
        try {
            c = CDB.find(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return c;
    }

    @Override
    public List<Computer> findAll() throws ServiceException {
        LOG.debug("find all computers");
        List<Computer> l = null;
        try {
            l = CDB.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return l;
    }

    @Override
    public Computer update(Computer t) throws ServiceException {
        try {
            ValidatorComputer v = ValidatorComputer.INSTANCE;
            if (!v.isValid(t)) {
                return null;
            }
            CDB.update(t);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return t;
    }

    @Override
    public boolean delete(Computer t) throws ServiceException {
        try {
            CDB.delete(t);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Computer create(Computer t) throws ServiceException {
        Computer c = null;
        try {
            ValidatorComputer v = ValidatorComputer.INSTANCE;
            if (!v.isValid(t)) {
                return null;
            }
            c = CDB.create(t);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return c;
    }

    /**
     * Method to count all computers in database.
     *
     * @return number of computers
     * @throws DAOException
     *             Exception
     */
    public int count() throws ServiceException {
        int c = 0;
        try {
            c = CDB.count();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return c;
    }

    /**
     * Method to search by name with order.
     *
     * @param current
     *            page
     * @param elementsByPage
     *            number of elements by page
     * @param search
     *            search if specified specified
     * @param order
     *            order of results
     * @return list of computers
     */
    public ArrayList<Computer> findBySearch(int current, int elementsByPage, String search, String order) {
        List<Computer> l = null;
        try {
            l = CDB.findBySearch(current, elementsByPage, search, order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return (ArrayList<Computer>) l;
    }

    public long countBySearch(String search) {
        return CDB.countBySearch(search);
    }

}
