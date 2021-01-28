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
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Tag> findAll(@RequestParam(required = false) MultiValueMap<String, String> params) throws DaoException {
        try {
            return tagService.getByParameters(params);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Tag add(@RequestBody @Validated Tag tag) throws DaoException {
        try {
            return tagService.add(tag);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public boolean delete(@RequestBody Tag tag) throws DaoException {
        try {
            return tagService.delete(tag);
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }


}
