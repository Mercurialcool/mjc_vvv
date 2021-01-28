package com.epam.esm.service.impl;

import com.epam.esm.dao.DaoException;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;


import java.util.List;
@Service
public class TagServiceImpl implements TagService {

    private TagDao tagDao;
    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> getByParameters(MultiValueMap<String, String> params) throws DaoException {
        try {
            return tagDao.getAll();
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Tag add(Tag tag) throws DaoException {
        try {
            return tagDao.add(tag);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    public boolean delete(Tag tag) throws DaoException {
        try {
            tagDao.delete(tag);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public Tag getByName(String name) throws DaoException {
        try {
            return tagDao.getByName(name);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }
}
