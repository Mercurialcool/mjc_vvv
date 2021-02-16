package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;
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
    private Converter<Tag, TagDto> tagConverter;
    @Autowired
    public TagServiceImpl(TagDao tagDao, Converter<Tag, TagDto> tagConverter) {
        this.tagConverter = tagConverter;
        this.tagDao = tagDao;
    }

    @Override
    public List<TagDto> getByParameters(MultiValueMap<String, String> params) throws DaoException {
        try {
            if (CollectionUtils.isEmpty(params))
                return tagConverter.objectDtoList(tagDao.getAll());
            return tagConverter.objectDtoList(tagDao.getByParameters(params));
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Transactional
    @Override
    public TagDto add(TagDto tagDto) throws DaoException {
        try {
            if(tagDao.getByName(tagDto.getName())!= null){
                throw new TagAlreadyExistsException("Tag already exists");
            }
            Tag tag = tagConverter.dtoObject(tagDto);
            return tagConverter.objectDto(tagDao.add(tag));
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Transactional
    @Override
    public void delete(TagDto tagDto, Long id) throws ServiceException, DaoException {
            if(tagDao.getById(id) == null) {
                throw new TagNotFoundException("Tag not found");
            }

            final Tag quantity = tagDao.getById(id);
            if(quantity == null) {
                throw new UnableToDeleteTagException("Unable to delete tag");
            }
            tagDao.getById(id);
            tagDto.setId(id);
            Tag tag = tagConverter.dtoObject(tagDto);
            tagDao.delete(tag);
    }

    @Override
    public TagDto getByName(String name) throws DaoException {
            Tag tag = tagDao.getByName(name);
            if (tag == null) {
                throw new TagNotFoundException("Tag not found");
            }
            return tagConverter.objectDto(tagDao.getByName(name));
    }

    @Override
    public TagDto getById(Long id) throws ServiceException {
            Tag tag = tagDao.getById(id);
            if (tag == null) {
                throw new TagNotFoundException("Tag not found");
            }
            return tagConverter.objectDto(tagDao.getById(id));
    }
}
