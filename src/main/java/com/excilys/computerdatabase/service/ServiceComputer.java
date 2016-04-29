package com.excilys.computerdatabase.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.persistence.ComputerDB;
import com.excilys.computerdatabase.validator.ValidatorComputer;

public class ServiceComputer implements IService<Computer> {

    static final Logger LOG = LoggerFactory.getLogger(ServiceComputer.class);
    private static final ComputerDB CDB = ComputerDB.getComputerDb();
    private static ServiceComputer sc = null;

    /**
     * Class constructor.
     */
    private ServiceComputer() {
    }

    /**
     * Method getInstance for the Singleton pattern.
     *
     * @return ServiceComputer
     */
    public static ServiceComputer getInstance() {
        if (sc == null) {
            synchronized (ServiceComputer.class) {
                if (sc == null) {
                    sc = new ServiceComputer();
                }
            }
        }
        return sc;
    }

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
            ValidatorComputer v = ValidatorComputer.getInstance();
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
            ValidatorComputer v = ValidatorComputer.getInstance();
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
     * Method to retrieve all computers for pagination.
     *
     * @param page
     *            page number
     * @param number
     *            number of result to retrieve
     * @return List of computers
     * @throws DAOException
     *             Exception
     */
    public List<Computer> findAll(int page, int number) throws ServiceException {
        List<Computer> l = null;
        try {
            l = CDB.findAll(page, number);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return l;
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

}
