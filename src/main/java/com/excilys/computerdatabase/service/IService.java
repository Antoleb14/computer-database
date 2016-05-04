package com.excilys.computerdatabase.service;

import java.util.List;

public interface IService<T> {

    /**
     * Method to find an entity.
     *
     * @param id
     *            entity ID
     * @return Entity
     */
    T find(Long id);

    /**
     * Method to retrieve all entities.
     *
     * @return List of entities
     */
    List<T> findAll();

    /**
     * Method to update an entity.
     *
     * @param t
     *            Entity
     * @return Entity
     */
    T update(T t);

    /**
     * Method to delete an entity.
     *
     * @param t
     *            Entity
     * @return boolean
     */
    boolean delete(T t);

    /**
     * Method to create an entity.
     *
     * @param t
     *            Entity
     * @return Entity
     */
    T create(T t);

}
