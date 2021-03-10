package com.epam.esm.controller;

import com.epam.esm.dao.SearchQuery;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.service.TagService;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.util.impl.TagHateoasBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private static final Logger logger = LogManager.getLogger(TagController.class);

    @Autowired
    private TagHateoasBuilder tagHateoasBuilder;

    @Autowired
    private TagService tagService;

    /**
     * Gets all tags
     * @param searchQuery MVM of strings to get all tags
     * @return List of tags
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<TagDto> getByParameters(SearchQuery searchQuery) {
        List<TagDto> list = tagService.getByParameters(searchQuery);
        tagHateoasBuilder.buildToEntitiesCollection(list);
        return list;
    }

    /**
     * Adds a new tag to list
     * @param tagDto
     * @return tag object
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.POST)
    public TagDto add(@RequestBody @Validated TagDto tagDto) {
        try {
            return tagService.add(tagDto);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Finds Tag by ID
     * @param id An identifier to perform an operation by
     * @return Tag
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public TagDto getById(@PathVariable Long id) {
        TagDto tagDto = tagService.getById(id);
        tagHateoasBuilder.buildForMainEntity(tagDto);
        return tagDto;
    }

    /**
     * Deletes a tag
     * @param tagDto tag object
     * @param id an to perform an operation by
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@RequestBody TagDto tagDto, @PathVariable Long id) {
        try {
            tagService.delete(tagDto, id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/mostFrequentTag")
    public List<TagDto> getMostFrequentTag() {
        List<TagDto> list = tagService.getMostFrequentTag();
        tagHateoasBuilder.buildToEntitiesCollection(list);
        return list;
    }
}
