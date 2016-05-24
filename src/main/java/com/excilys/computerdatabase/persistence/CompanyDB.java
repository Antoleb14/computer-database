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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.mapper.CompanyMapper;

/**
 * DAO class for Company.
 *
 * @author excilys
 *
 */

@Repository("companyDB")
@Scope("singleton")
public class CompanyDB implements EntityDB<Company> {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyDB.class);

    @Autowired
    @Qualifier("sqlutils")
    private SQLUtils sc;

    @Autowired
    @Qualifier("companyMapper")
    private CompanyMapper cmapper;

    private JdbcTemplate jdbcTemplate;

    /**
     * Constructor of the class.
     */
    public CompanyDB() {
    }

    @Override
    public Connection connect() {
        return sc.getConnection();
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
            list = cmapper.mapAll(res);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection();
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
                c = cmapper.map(res);
            }
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection();
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
    public int delete(Company c) throws DAOException {
        LOG.debug("DELETING Company: " + c.getName());
        String query = "DELETE FROM company WHERE id = ?";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.queryForObject(query, new Object[] { c.getId() }, Integer.class);
    }

}
