package com.epam.esm.controller;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private static final Logger logger = LogManager.getLogger(TagController.class);


    @Autowired
    private TagService tagService;

    /**
     * Gets all tags
     * @param params MVM of strings to get all tags
     * @return List of tags
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> getByParameters(@RequestParam(required = false) MultiValueMap<String, String> params) {
        try {
            return tagService.getByParameters(params);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Adds a new tag to list
     * @param tag
     * @return tag object
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.POST)
    public Tag add(@RequestBody @Validated Tag tag) {
        try {
            return tagService.add(tag);
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
    public Tag getCertificateById(@PathVariable Long id) {
        try {
            return tagService.getById(id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Deletes a tag
     * @param tag tag object
     * @param id an to perform an operation by
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@RequestBody Tag tag, @PathVariable Long id) {
        try {
            tagService.delete(tag, id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }
}
