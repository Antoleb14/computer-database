package com.excilys.computerdatabase.persistence;

import java.util.List;

import com.excilys.computerdatabase.entity.Computer;
import com.excilys.computerdatabase.exception.DAOException;
import com.excilys.computerdatabase.service.Order;

/**
 * Abstract class for DAOs with connection and disconnection method to invoke in
 * each method of the DAO.
 *
 * @author excilys
 *
 */
public interface IComputerDB<T> {

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
     * Method to delete an entity in the database.
     *
     * @param c
     *            Entity to persist
     * @return boolean
     */
    int delete(T c) throws DAOException;

    /**
     * Method to delete an entity in the database.
     *
     * @param c
     *            Entity to persist
     * @return boolean
     */
    int deleteByCompany(Long id) throws DAOException;

    public abstract List<Computer> findBySearch(int current, int elementsByPage, String search, Order order);

    public abstract long countBySearch(String search);

    public abstract List<Computer> findByName(String name);

    public abstract int delete(List<Long> ls);

}
