package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.persistence.CompanyDB;

public class ServiceCompany implements IService<Company> {

    private static final CompanyDB CDB = CompanyDB.getCompanyDb();
    private static ServiceCompany sc = null;

    /**
     * Class constructor.
     */
    private ServiceCompany() {
    }

    /**
     * Method getInstance for Singleton pattern.
     *
     * @return ServiceCompany
     */
    public static ServiceCompany getInstance() {
        if (sc == null) {
            synchronized (ServiceCompany.class) {
                if (sc == null) {
                    sc = new ServiceCompany();
                }
            }
        }
        return sc;
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

    @Override
    public boolean delete(Company t) throws ServiceException {
        return CDB.delete(t);
    }

    @Override
    public Company create(Company t) throws ServiceException {
        return CDB.create(t);
    }
}
