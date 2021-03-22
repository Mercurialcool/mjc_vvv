package com.epam.esm.dao;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Tag;

import java.util.List;
import java.util.Set;

/**
 * Interface for CRUD operations of Tag entity
 * @param
 */

public interface TagDao extends CrudDao<Tag, Long>{
    public Set<Tag> getTagsByIds(List<Long> ids) throws DaoException;
    public List<Tag> getMostFrequentTag() throws DaoException;
}
