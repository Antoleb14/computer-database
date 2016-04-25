package com.excilys.computerdatabase.service;

import java.util.List;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.DAOException;
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
            synchronized (ServiceComputer.class) {
                if (sc == null) {
                    sc = new ServiceCompany();
                }
            }
        }
        return sc;
    }

    @Override
    public Company find(Long id) throws DAOException {
        return CDB.find(id);
    }

    @Override
    public List<Company> findAll() throws DAOException {
        return CDB.findAll();
    }

    @Override
    public boolean isValid(Company t) throws DAOException {
        if (t.getName().equals("")) {
            return false;
        }
        return true;

    }

    @Override
    public Company update(Company t) throws DAOException {
        if (!isValid(t)) {
            return null;
        }
        CDB.update(t);
        return t;
    }

    @Override
    public boolean delete(Company t) throws DAOException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Company create(Company t) throws DAOException {
        // TODO Auto-generated method stub
        return null;
    }
}
