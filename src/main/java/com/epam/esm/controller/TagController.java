package com.epam.esm.controller;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateServiceException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appTag")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(path = "/findAll", method = RequestMethod.GET)
    public List<Tag> findAll() throws DaoException {
        try {
            return tagService.findAll();
        } catch (CertificateServiceException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/addNewTag", method = RequestMethod.POST)
    public Tag add(@RequestBody @Validated Tag tag) throws DaoException {
        try {
            return tagService.add(tag);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @RequestMapping(path = "/removeTag", method = RequestMethod.DELETE)
    public boolean delete(@RequestBody Tag tag) throws CertificateServiceException, DaoException {
        try {
            return tagService.delete(tag);
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }


}
