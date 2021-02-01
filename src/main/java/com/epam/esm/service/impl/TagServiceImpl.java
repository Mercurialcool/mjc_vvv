package com.epam.esm.service.impl;

import com.epam.esm.dao.DaoException;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateServiceException;
import com.epam.esm.service.TagService;
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
            return tagDao.add(tag);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Tag tag, Long id) throws CertificateServiceException, DaoException {
        try {
            tagDao.getById(id);
            tag.setId(id);
            tagDao.delete(tag);
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
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
