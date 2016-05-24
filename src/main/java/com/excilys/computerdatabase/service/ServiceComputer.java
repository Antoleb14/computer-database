package com.excilys.computerdatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.persistence.ComputerDB;
import com.excilys.computerdatabase.validator.ValidatorComputer;

@Service("serviceComputer")
@Scope("singleton")
public class ServiceComputer implements IService<Computer> {

    private Logger LOG = LoggerFactory.getLogger(ServiceComputer.class);

    @Autowired
    @Qualifier("computerDB")
    private ComputerDB CDB;

    @Autowired
    @Qualifier("validatorComputer")
    public ValidatorComputer validator;

    /**
     * Constructor of the class.
     */
    public ServiceComputer() {
    }

    @Override
    public Computer find(Long id) {
        // LOG.debug("find a Computer id : " + id);
        Computer c = null;
        try {
            c = CDB.find(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return c;
    }

    @Override
    public List<Computer> findAll() {
        // LOG.debug("find all computers");
        List<Computer> l = null;
        try {
            l = CDB.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return l;
    }

    @Override
    public Computer update(Computer t) {
        try {
            if (!validator.isValid(t)) {
                return null;
            }
            CDB.update(t);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return t;
    }

    @Override
    public boolean delete(Computer t) {
        try {
            CDB.delete(t);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    public boolean delete(List<Long> ls) {
        try {
            CDB.delete(ls);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Computer create(Computer t) {
        Computer c = null;
        try {
            if (!validator.isValid(t)) {
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
    public int count() {
        int c = 0;
        try {
            c = CDB.count();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return c;
    }

    /**
     * Method to find a computer by name.
     *
     * @param current
     *            name to find
     * @return list of computers
     */
    public List<Computer> findByName(String name) {
        List<Computer> l = null;
        try {
            l = CDB.findByName(name);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return l;
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
    public ArrayList<Computer> findBySearch(int current, int elementsByPage, String search, Order order) {
        // LOG.debug("find by search");
        try {
            return (ArrayList<Computer>) CDB.findBySearch(current, elementsByPage, search, order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method to count the number of rows of a search.
     *
     * @param search
     *            search
     * @return number of rows
     */
    public long countBySearch(String search) {
        return CDB.countBySearch(search);
    }

}
