package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.exception.tag.TagAlreadyExistsException;
import com.epam.esm.service.exception.tag.TagNotFoundException;
import com.epam.esm.service.exception.tag.UnableToDeleteTagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;


import java.util.List;
@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private TagDao tagDao;
    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<Tag> getByParameters(MultiValueMap<String, String> params) throws DaoException {
        try {
            if (CollectionUtils.isEmpty(params))
                return tagDao.getAll();
            return tagDao.getByParameters(params);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Transactional
    @Override
    public Tag add(Tag tag) throws DaoException {
        try {
            if(tagDao.getByName(tag.getName())!= null){
                throw new TagAlreadyExistsException("Tag already exists");
            }
            return tagDao.add(tag);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Tag tag, Long id) throws ServiceException, DaoException {
        try {
            if(tagDao.getById(id) == null) {
                throw new TagNotFoundException("Tag not found");
            }

            final Tag quantity = tagDao.getById(id);
            if(quantity == null) {
                throw new UnableToDeleteTagException("Unable to delete tag");
            }
            tagDao.getById(id);
            tag.setId(id);
            tagDao.delete(tag);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Tag getByName(String name) throws DaoException {
        try {
            Tag tag = tagDao.getByName(name);
            if (tag == null) {
                throw new TagNotFoundException("Tag not found");
            }
            return tagDao.getByName(name);
        } catch (DaoException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Tag getById(Long id) throws ServiceException {
        try {
            Tag tag = tagDao.getById(id);
            if (tag == null) {
                throw new TagNotFoundException("Tag not found");
            }
            return tagDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
