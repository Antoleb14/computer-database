package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.mapper.ComputerMapper;

/**
 * DAO class for Computer.
 *
 * @author excilys
 *
 */
public class ComputerDB implements EntityDB<Computer> {

    private static ComputerDB cdb = null;

    /**
     * Class constructor of the class.
     */
    private ComputerDB() {

    }

    /**
     * Method to get instance of ComputerDb or create one if null.
     *
     * @return ComputerDB
     */
    public static synchronized ComputerDB getComputerDb() {
        if (cdb == null) {
            cdb = new ComputerDB();
        }
        return cdb;
    }

    /**
     * Method to persist a new Computer in the database.
     *
     * @param c
     *            Object Computer
     * @return boolean success of the operation
     */
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
            closeConnection(db);
        }
        return c;
    }

    /**
     * Method to update a computer in the database.
     *
     * @param c
     *            Computer
     * @return boolean success of the operation
     */
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
            closeConnection(db);
        }
        return c;
    }

    /**
     * Method to find a Computer by ID.
     *
     * @param id
     *            ID of the Computer
     * @return Computer found
     */
    @Override
    public Computer find(Long id) {
        Connection db = connect();
        PreparedStatement prep = null;
        ResultSet res = null;
        Computer c = null;
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.id = ?";
        try {
            prep = db.prepareStatement(query);
            prep.setLong(1, id);
            res = prep.executeQuery();
            c = ComputerMapper.getInstance().map(res);
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
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
    public List<Computer> findByName(String name) {
        Connection db = connect();
        PreparedStatement prep = null;
        ResultSet res = null;
        List<Computer> c = new ArrayList<Computer>();
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id WHERE i.name = ? ORDER BY i.id ASC";
        try {
            prep = db.prepareStatement(query);
            prep.setString(1, name);
            res = prep.executeQuery();
            c = ComputerMapper.getInstance().mapAll(res);
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }

        return c;
    }

    /**
     * Method to find all Computers in the database.
     *
     * @return List of computers
     */
    @Override
    public List<Computer> findAll() {
        Connection db = connect();
        ResultSet res = null;
        List<Computer> list = new ArrayList<Computer>();
        try {
            Statement statement = db.createStatement();
            res = statement.executeQuery(
                    "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id ORDER BY i.name ASC");
            list = ComputerMapper.getInstance().mapAll(res);
            statement.close();
            res.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }
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
    public List<Computer> findAll(int page, int number) {
        Connection db = connect();
        List<Computer> list = new ArrayList<Computer>();
        PreparedStatement prep = null;
        ResultSet res = null;
        String query = "SELECT * FROM computer i LEFT JOIN company c ON c.id = i.company_id ORDER BY i.name ASC LIMIT ?, ?";

        try {
            prep = db.prepareStatement(query);
            prep.setInt(1, page);
            prep.setInt(2, number);
            res = prep.executeQuery();

            list = ComputerMapper.getInstance().mapAll(res);
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }
        return list;
    }

    /**
     * Method to delete a computer in the database.
     *
     * @param cmp
     *            Computer to delete
     * @return boolean for the success of the operation
     */
    @Override
    public boolean delete(Computer cmp) {
        Connection db = connect();
        PreparedStatement prep = null;
        String query = "DELETE FROM computer WHERE id = ?";
        try {
            prep = db.prepareStatement(query);
            prep.setLong(1, cmp.getId());
            prep.executeUpdate();
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }
        return true;
    }

    /**
     * Method to return the total number of computers in the database.
     *
     * @return integer number of computers
     */
    public int count() {
        Connection db = connect();
        PreparedStatement prep = null;
        ResultSet res = null;

        int nb = 0;
        String query = "SELECT COUNT(*) FROM computer";
        try {
            prep = db.prepareStatement(query);
            res = prep.executeQuery();
            if (res.next()) {
                nb = res.getInt(1);
            }
            prep.close();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            closeConnection(db);
        }

        return nb;
    }

}
