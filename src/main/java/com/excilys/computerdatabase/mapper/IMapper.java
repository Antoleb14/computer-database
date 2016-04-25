package com.excilys.computerdatabase.mapper;

import java.sql.ResultSet;
import java.util.List;

public interface IMapper<T> {

    /**
     * Method to map a resultSet to an entity.
     *
     * @param rs
     *            ResultSet
     * @return Entity
     */
    T map(ResultSet rs);

    /**
     * Method to map a resultset to a list of entities.
     *
     * @param rs
     *            ResultSet
     * @return List of entities
     */
    List<T> mapAll(ResultSet rs);

}
