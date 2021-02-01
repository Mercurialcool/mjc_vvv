package com.epam.esm.controller;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * Gets all tags
     * @param params MVM of strings to get all tags
     * @return List of tags
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> findAll(@RequestParam(required = false) MultiValueMap<String, String> params) throws DaoException {
        try {
            return tagService.getByParameters(params);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Adds a new tag to list
     * @param tag
     * @return tag object
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.POST)
    public Tag add(@RequestBody @Validated Tag tag) throws DaoException {
        try {
            return tagService.add(tag);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Deletes a tag
     * @param tag tag object
     * @param id an to perform an operation by
     * @throws DaoException
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@RequestBody Tag tag, @PathVariable Long id) throws DaoException {
        try {
            tagService.delete(tag, id);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }


}
