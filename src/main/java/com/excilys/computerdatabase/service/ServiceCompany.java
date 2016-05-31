package com.excilys.computerdatabase.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.persistence.ICompanyDB;
import com.excilys.computerdatabase.persistence.IComputerDB;
import com.excilys.computerdatabase.persistence.SQLUtils;

@Service("serviceCompany")
public class ServiceCompany implements IService<Company> {

    private Logger LOG = LoggerFactory.getLogger(ServiceComputer.class);

    @Autowired
    private IComputerDB<Computer> CDC;

    @Autowired
    private ICompanyDB<Company> CDB;

    @Autowired
    @Qualifier("sqlutils")
    private SQLUtils sql;

    /**
     * Constructor of the class.
     */
    public ServiceCompany() {
    }

    @Override
    public Company find(Long id) throws ServiceException {
        Company c = null;
        try {
            c = CDB.find(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return c;
    }

    @Override
    public List<Company> findAll() throws ServiceException {
        List<Company> l = null;
        try {
            l = CDB.findAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return l;
    }

    public boolean isValid(Company t) throws ServiceException {
        if (t.getName().equals("")) {
            return false;
        }
        return true;

    }

    @Override
    public Company update(Company t) throws ServiceException {
        if (!isValid(t)) {
            return null;
        }
        try {
            CDB.update(t);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return t;
    }

    @Transactional
    @Override
    public boolean delete(Company t) throws ServiceException {
        CDC.deleteByCompany(t.getId());
        CDB.delete(t);
        return true;
    }

    @Override
    public Company create(Company t) throws ServiceException {
        return CDB.create(t);
    }
}
