package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateSpecification;
import com.epam.esm.dao.CustomTagRepository;
import com.epam.esm.dao.SearchQuery;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.exception.tag.TagAlreadyExistsException;
import com.epam.esm.service.exception.tag.TagNotFoundException;
import com.epam.esm.service.exception.tag.UnableToDeleteTagException;
import com.epam.esm.service.utils.SearchQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TagServiceImpl implements TagService {

    private TagDao tagDao;
    private Converter<Tag, TagDto> tagConverter;
    private CustomTagRepository customTagRepository;

    @Autowired
    public TagServiceImpl(TagDao tagDao,
                          Converter<Tag, TagDto> tagConverter,
                          CustomTagRepository customTagRepository) {
        this.tagConverter = tagConverter;
        this.tagDao = tagDao;
        this.customTagRepository = customTagRepository;
    }

    @Override
    public List<TagDto> getByParameters(SearchQuery searchQuery) throws DaoException {
        Pageable pageable = SearchQueryUtil.getPage(searchQuery);
        return tagConverter.convertObjectListToDto(
                customTagRepository.findAll(new CertificateSpecification<>(searchQuery), pageable).toList());
    }

    @Transactional
    @Override
    public TagDto add(TagDto tagDto) throws DaoException {
        try {
            if(tagDao.getByName(tagDto.getName())!= null){
                throw new TagAlreadyExistsException("Tag already exists");
            }
            Tag tag = tagConverter.convertDtoToObject(tagDto);
            return tagConverter.convertObjectToDto(tagDao.add(tag));
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
            Tag tag = tagConverter.convertDtoToObject(tagDto);
            tagDao.delete(tag);
    }

    @Override
    public TagDto getByName(String name) throws DaoException {
            Tag tag = tagDao.getByName(name);
            if (tag == null) {
                throw new TagNotFoundException("Tag not found");
            }
            return tagConverter.convertObjectToDto(tagDao.getByName(name));
    }

    @Override
    public TagDto getById(Long id) throws ServiceException {
            Tag tag = tagDao.getById(id);
            if (tag == null) {
                throw new TagNotFoundException("Tag not found");
            }
            return tagConverter.convertObjectToDto(tagDao.getById(id));
    }

    @Override
    public List<TagDto> getMostFrequentTag() throws ServiceException {
        return tagDao.getMostFrequentTag().stream().map(tagConverter::convertObjectToDto).collect(Collectors.toList());
    }
}
