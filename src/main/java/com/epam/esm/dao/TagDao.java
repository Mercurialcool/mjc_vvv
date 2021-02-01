package com.epam.esm.dao;

import com.epam.esm.model.Tag;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Interface for CRUD operations of Tag entity
 * @param
 */

public interface TagDao extends CrudDao<Tag>{

    Tag mapRow(ResultSet resultSet, int i) throws SQLException;
}
