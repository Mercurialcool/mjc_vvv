package com.epam.esm.service;

import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ServiceException;

import java.util.List;

/**
 * Interface for TagService entity
 */

public interface TagService extends CrudService<TagDto> {
    public List<TagDto> getMostFrequentTag() throws ServiceException;
}
