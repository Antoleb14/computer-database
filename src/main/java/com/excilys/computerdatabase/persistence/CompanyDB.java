package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.mapper.CompanyMapper;

/**
 * DAO class for Company.
 *
 * @author excilys
 *
 */
public class CompanyDB implements EntityDB<Company> {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyDB.class);
    private static CompanyDB cdb = null;

    /**
     * Constructor of the class.
     */
    private CompanyDB() {
    }

    /**
     * Method to get instance of ComputerDb or create one if null.
     *
     * @return ComputerDB
     */
    public static synchronized CompanyDB getCompanyDb() {
        if (cdb == null) {
            cdb = new CompanyDB();
        }
        return cdb;
    }

    /**
     * Return all companies stored in the database.
     *
     * @return List of companies
     */
    @Override
    public List<Company> findAll() {
        Connection db = connect();
        ResultSet res = null;
        List<Company> list = new ArrayList<Company>();
        try {
            Statement statement = db.createStatement();
            res = statement.executeQuery("SELECT * FROM company ORDER BY name ASC");
            list = CompanyMapper.getInstance().mapAll(res);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }
        return list;
    }

    /**
     * Find a company by ID.
     *
     * @param id
     *            ID of the company
     * @return Company
     */
    @Override
    public Company find(Long id) {
        Connection db = connect();
        PreparedStatement prep = null;
        ResultSet res = null;
        Company c = null;
        String query = "SELECT id, name FROM company c WHERE id = ?";
        try {
            prep = db.prepareStatement(query);
            prep.setLong(1, id);
            res = prep.executeQuery();
            if (res.next()) {
                c = CompanyMapper.getInstance().map(res);
            }
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }
        return c;
    }

    /**
     * Method to update a company.
     *
     * @param c
     *            Company
     */
    @Override
    public Company update(Company c) {
        // Not Implemented
        return null;
    }

    /**
     * Method to create a company.
     *
     * @param c
     *            Company
     */
    @Override
    public Company create(Company c) {
        // Not Implemented
        return null;
    }

    /**
     * Method to delete a company.
     *
     * @param c
     *            Company
     */
    @Override
    public boolean delete(Company c) {
        LOG.debug("DELETING Company: " + c.getName());
        Connection db = connect();
        PreparedStatement prep = null;
        String query2 = "DELETE FROM company WHERE id = ?";
        String query = "DELETE FROM computer WHERE company_id = ?";
        try {
            db.setAutoCommit(false);
            prep = db.prepareStatement(query);
            prep.setLong(1, c.getId());
            prep.executeUpdate();
            prep.close();
            prep = db.prepareStatement(query2);
            prep.setLong(1, c.getId());
            prep.executeUpdate();
            prep.close();
            db.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage());
            try {
                db.rollback();
            } catch (SQLException e1) {
                throw new DAOException(e);
            }
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }
        return true;
    }

}
