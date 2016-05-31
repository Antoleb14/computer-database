package com.excilys.computerdatabase.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
@Scope("singleton")
public class CompanyDB implements ICompanyDB<Company> {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyDB.class);

    @Autowired
    @Qualifier("sqlutils")
    private SQLUtils sc;

    @Autowired
    @Qualifier("companyMapper")
    private CompanyMapper cmapper;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Constructor of the class.
     */
    public CompanyDB() {
    }

    protected final Session getSession() {
        try {
            Session session = sessionFactory.getCurrentSession();
            return session;
        } catch (HibernateException e) {
            Session session = sessionFactory.openSession();
            return session;
        }
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    /**
     * Return all companies stored in the database.
     *
     * @return List of companies
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Company> findAll() {
        String hql = "SELECT i FROM Company as i";
        Session s = getSession();
        Query query = s.createQuery(hql);
        List<Company> ls = query.list();
        return ls;
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
        Session s = getSession();
        Company c = (Company) s.get(Company.class, id);
        System.out.println("Getting company " + c.getName());
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
        Session s = getSession();
        s.delete(c);
        s.flush();
        return 1;
    }

}
