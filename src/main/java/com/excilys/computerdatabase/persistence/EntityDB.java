package com.excilys.computerdatabase.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.computerdatabase.exception.DAOException;

/**
 * Abstract class for DAOs with connection and disconnection method to invoke in
 * each method of the DAO.
 *
 * @author excilys
 *
 */
public interface EntityDB<T> {

    /**
     * Method to find an Entity by id.
     *
     * @param id
     *            ID of an entity
     * @return Entity
     */
    public abstract T find(Long id);

    /**
     * Method to retrieve all entities.
     *
     * @return List of entities
     */
    public abstract List<T> findAll();

    /**
     * Method to update an entity.
     *
     * @param c
     *            Entity
     * @return Entity
     */
    public abstract T update(T c);

    /**
     * Method to persist an entity in the database.
     *
     * @param c
     *            Entity
     * @return Entity created
     */
    public abstract T create(T c);

    /**
     * Method to connect to database.
     */
    public default Connection connect() {
        return SQLUtils.INSTANCE.getConnection();
    }

    /**
     * Method to close a connection to the database.
     */
    public default void closeConnection() {
        SQLUtils.INSTANCE.close();
    }

    /**
     * Method to delete an entity in the database.
     *
     * @param c
     *            Entity to persist
     * @return boolean
     */
    boolean delete(T c) throws DAOException;

}
