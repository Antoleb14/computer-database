package com.excilys.computerdatabase.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.Order;

/**
 * DAO class for Computer.
 *
 * @author excilys
 *
 */

@Repository("computerDB")
@Transactional
@Scope("singleton")
public class ComputerDB implements IComputerDB<Computer> {

    @Autowired
    @Qualifier("computerMapper")
    private ComputerMapper cmapper;

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Constructor of the class.
     */
    public ComputerDB() {
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

    @Override
    public Computer create(Computer c) {
        Session s = getSession();
        s.save(c);
        return c;
    }

    @Override
    public Computer update(Computer c) {
        Session s = getSession();
        System.out.println(c);
        s.update(c);
        s.flush();
        return c;
    }

    @Override
    public Computer find(Long id) {
        return (Computer) getSession().get(Computer.class, id);
    }

    /**
     * Method to find a Computer by Name. ,
     *
     * @param name
     *            Name of the Computer
     * @return List of Computers found
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Computer> findByName(String name) {
        String hql = "SELECT i FROM Computer as i WHERE name = :name ORDER BY i.id ASC";
        Session s = getSession();
        Query query = s.createQuery(hql);
        query.setParameter("name", name);
        List<Computer> ls = query.list();
        return ls;
    }

    /**
     * Method to find a Computer by Name.
     *
     * @param name
     *            Name of the Computer
     * @return List of Computers found
     */
    @SuppressWarnings("unchecked")
    public List<Computer> findByCompany(String id) {
        String hql = "SELECT i FROM Computer as i left join i.Company as c WHERE c.id = :id ORDER BY i.id ASC";
        Session s = getSession();
        Query query = s.createQuery(hql);
        query.setParameter("id", id);
        List<Computer> ls = query.list();
        return ls;
    }

    /**
     * Method to find a Computer by Name.
     *
     * @param name
     *            Name of the Computer
     * @return List of Computers found
     */
    @SuppressWarnings("unchecked")
    public List<Computer> findByCompany(Company t) {
        String hql = "SELECT i FROM Computer as i WHERE i.company = :company ORDER BY i.id ASC";
        Session s = getSession();
        Query query = s.createQuery(hql);
        query.setParameter("company", t);
        List<Computer> ls = query.list();
        return ls;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Computer> findAll() {
        String hql = "SELECT i FROM Computer as i";
        Session s = getSession();
        Query query = s.createQuery(hql);
        List<Computer> ls = query.list();
        return ls;
    }

    /**
     * Method to find all computers by range for pagination.
     *
     * @param page
     *            page requested
     * @param number
     *            number of computers by page
     * @return List of computers
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Computer> findBySearch(int page, int number, String search, Order order) {
        String orderQuery = "";
        StringBuilder sb = new StringBuilder();

        String query = "select i from Computer as i left join i.company as c WHERE i.name LIKE :searchQ OR c.name LIKE :searchQ";

        if (order != null) {
            if (order.getChamp().equals("company")) {
                orderQuery = " ORDER BY c.name " + order.getOrder() + " ";
            } else if (order.getChamp().matches("name|introduced|discontinued")) {
                orderQuery = " ORDER BY i." + order.getChamp() + " " + order.getOrder() + " ";
            }
        }
        sb.append(query).append(orderQuery);

        String searchQ = search + "%";
        Query hql = getSession().createQuery(sb.toString());
        hql.setParameter("searchQ", searchQ);
        hql.setFirstResult(page);
        hql.setMaxResults(number);

        List<Computer> ls = hql.list();
        return ls;
        // jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        // return jdbcTemplate.query(sb.toString(), new Object[] { searchQ,
        // searchQ, page, number }, new ComputerMapper());
    }

    @Override
    public int delete(Computer cmp) {
        Session s = getSession();
        Query query = s.createQuery("delete Computer where id = :ID");
        query.setParameter("ID", cmp.getId());
        int result = query.executeUpdate();
        return result;
    }

    @Override
    public int delete(List<Long> cmp) {
        Session s = getSession();

        for (int i = 0; i < cmp.size(); i++) {
            Computer comp = find(cmp.get(i));
            s.delete(comp);
        }
        s.flush();
        return cmp.size();

    }

    /**
     * Method to delete a computer in the database.
     *
     * @param db2
     *
     * @param cmp
     *            Computer to delete
     * @return boolean for the success of the operation
     */
    @Override
    public int deleteByCompany(Long id) {
        Session s = getSession();
        Query query = s.createQuery("delete Computer where company_id = :ID");
        query.setParameter("ID", id);
        int result = query.executeUpdate();
        return result;
    }

    /**
     * Method to return the total number of computers in the database.
     *
     * @return integer number of computers
     */

    @Override
    public long countBySearch(String search) {
        String searchQuery = "WHERE i.name LIKE :search";// OR c.name LIKE
                                                         // :search";
        String hql = "SELECT COUNT(*) FROM Computer as i LEFT JOIN i.company as c ON c.id = i.company ";
        String searchQ = search + "%";
        Query query = null;
        if (search != null && !search.trim().isEmpty()) {
            hql += searchQuery;
            query = getSession().createQuery(hql);
            query.setParameter("search", searchQ);
        } else {
            query = getSession().createQuery(hql);
        }
        return (long) query.uniqueResult();
    }

}
