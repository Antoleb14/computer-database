package com.excilys.computerdatabase.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.exception.ServiceException;
import com.excilys.computerdatabase.persistence.CompanyDB;
import com.excilys.computerdatabase.persistence.ComputerDB;
import com.excilys.computerdatabase.persistence.SQLUtils;

public enum ServiceCompany implements IService<Company> {

    INSTANCE;

    private static final CompanyDB CDB = CompanyDB.getCompanyDb();
    private static final ComputerDB CDC = ComputerDB.INSTANCE;

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
        Connection db = SQLUtils.INSTANCE.getConnection();
        try {
            db.setAutoCommit(false);
            CDC.deleteByCompany(t.getId(), db);
            CDB.delete(t, db);
            db.commit();
        } catch (SQLException | DAOException e) {
            try {
                db.rollback();
            } catch (SQLException e1) {
                throw new ServiceException(e);
            }
        } finally {
            try {
                db.close();
            } catch (SQLException e) {
                throw new ServiceException(e);
            }
        }

        return true;
    }

    @Override
    public Company create(Company t) throws ServiceException {
        return CDB.create(t);
    }
}
