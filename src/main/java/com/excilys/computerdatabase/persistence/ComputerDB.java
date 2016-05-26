package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.computerdatabase.entity.Company;
import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.mapper.ComputerMapper;
import com.excilys.computerdatabase.service.Order;

/**
 * DAO class for Computer.
 *
 * @author excilys
 *
 */

@Repository("computerDB")
@Scope("singleton")
public class ComputerDB implements EntityDB<Computer> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("computerMapper")
    private ComputerMapper cmapper;

    @Autowired
    @Qualifier("sqlutils")
    private SQLUtils sc;

    /**
     * Constructor of the class.
     */
    public ComputerDB() {
    }

    @Override
    public Connection connect() {
        return sc.getConnection();
    }

    @Override
    public Computer create(Computer c) {
        Connection db = connect();
        PreparedStatement prep = null;
        String query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES(?, ?, ?, ?)";
        try {
            prep = db.prepareStatement(query);
            prep.setString(1, c.getName());
            if (c.getIntroduced() == null) {
                prep.setNull(2, java.sql.Types.TIMESTAMP);
            } else {
                prep.setTimestamp(2, Timestamp.valueOf(c.getIntroduced()));
            }
            if (c.getDiscontinued() == null) {
                prep.setNull(3, java.sql.Types.TIMESTAMP);
            } else {
                prep.setTimestamp(3, Timestamp.valueOf(c.getDiscontinued()));
            }
            if (c.getCompany() != null) {
                prep.setLong(4, c.getCompany().getId());
            } else {
                prep.setNull(4, java.sql.Types.INTEGER);
            }

            prep.executeUpdate();
            prep.close();
            List<Computer> ls = this.findByName(c.getName());
            c = ls.get(ls.size() - 1);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection();
        }
        return c;
    }

    @Override
    public Computer update(Computer c) {
        Connection db = connect();
        PreparedStatement prep = null;
        String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
        try {
            prep = db.prepareStatement(query);
            prep.setString(1, c.getName());
            if (c.getIntroduced() == null) {
                prep.setNull(2, java.sql.Types.TIMESTAMP);
            } else {
                prep.setTimestamp(2, Timestamp.valueOf(c.getIntroduced()));
            }
            if (c.getDiscontinued() == null) {
                prep.setNull(3, java.sql.Types.TIMESTAMP);
            } else {
                prep.setTimestamp(3, Timestamp.valueOf(c.getDiscontinued()));
            }

            if (c.getCompany() != null) {
                prep.setLong(4, c.getCompany().getId());
            } else {
                prep.setNull(4, java.sql.Types.INTEGER);
            }

            prep.setLong(5, c.getId());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection();
        }
        return c;
    }

    @Override
    public Computer find(Long id) {
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.id = ?";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.queryForObject(query, new Object[] { id }, new ComputerMapper());
    }

    /**
     * Method to find a Computer by Name. ,
     *
     * @param name
     *            Name of the Computer
     * @return List of Computers found
     */
    public List<Computer> findByName(String name) {
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.name = ? ORDER BY i.id ASC";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.query(query, new Object[] { name }, new ComputerMapper());
    }

    /**
     * Method to find a Computer by Name.
     *
     * @param name
     *            Name of the Computer
     * @return List of Computers found
     */
    public List<Computer> findByCompany(String id) {
        Connection db = connect();
        PreparedStatement prep = null;
        ResultSet res = null;
        List<Computer> c = new ArrayList<Computer>();
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.company_id = ? ORDER BY i.id ASC";
        try {
            prep = db.prepareStatement(query);
            prep.setString(1, id);
            res = prep.executeQuery();
            c = cmapper.mapAll(res);
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection();
        }

        return c;
    }

    /**
     * Method to find a Computer by Name.
     *
     * @param name
     *            Name of the Computer
     * @return List of Computers found
     */
    public List<Computer> findByCompany(Company t) {
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.company_id = ? ORDER BY i.id ASC";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.query(query, new Object[] { t.getId() }, new ComputerMapper());
    }

    @Override
    public List<Computer> findAll() {
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id ORDER BY i.name ASC";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        List<Computer> list = jdbcTemplate.query(query, new ComputerMapper());
        return list;
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
    public List<Computer> findBySearch(int page, int number, String search, Order order) {
        String orderQuery = "";
        StringBuilder sb = new StringBuilder();

        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.name LIKE ? OR c.name LIKE ?";

        if (order != null) {
            if (order.getChamp().equals("company")) {
                orderQuery = " ORDER BY c.name " + order.getOrder() + " ";
            } else if (order.getChamp().matches("name|introduced|discontinued")) {
                orderQuery = " ORDER BY i." + order.getChamp() + " " + order.getOrder() + " ";
            }
        }
        sb.append(query).append(orderQuery).append(" LIMIT ?, ?");

        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        String searchQ = search + "%";
        System.out.println(number);
        return jdbcTemplate.query(sb.toString(), new Object[] { searchQ, searchQ, page, number }, new ComputerMapper());
    }

    @Override
    public int delete(Computer cmp) {
        String query = "DELETE FROM computer WHERE id = ?";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.update(query, new Object[] { cmp.getId() });
    }

    public int delete(List<Long> cmp) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < cmp.size(); i++) {
            if (i < cmp.size() - 1) {
                sb.append("?, ");
            } else {
                sb.append("?");
            }
        }
        sb.append(")");
        cmp.toArray();
        Object[] objs = new Object[cmp.size()];
        for (int i = 0; i < cmp.size(); i++) {
            objs[i] = cmp.get(i);
        }

        String query = "DELETE FROM computer WHERE id IN " + sb.toString();
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.update(query, objs);
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
    public int deleteByCompany(Long id) {
        String query = "DELETE FROM computer WHERE company_id = ?";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.update(query, new Object[] { id });
    }

    /**
     * Method to return the total number of computers in the database.
     *
     * @return integer number of computers
     */
    public int count() {
        String query = "SELECT COUNT(*) FROM computer";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public int countBySearch(String search) {
        String searchQuery = "WHERE c.name LIKE ? OR i.name LIKE ?";
        String query = "SELECT COUNT(*) FROM computer i LEFT JOIN company c ON c.id = i.company_id " + searchQuery;
        String searchQ = search + "%";
        jdbcTemplate = new JdbcTemplate(sc.getDataSource());
        return jdbcTemplate.queryForObject(query, new Object[] { searchQ, searchQ }, Integer.class);
    }

}
