package com.epam.esm.controller;

import com.epam.esm.dao.SearchQuery;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.util.impl.CertificateHateoasBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {

    private static final Logger logger = LogManager.getLogger(GiftCertificateController.class);
    private CertificateService certificateService;

    @Autowired
    private CertificateHateoasBuilder certificateHateoasBuilder;

    @Autowired
    public GiftCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Extracts search query to get a list of certificates
     * @param
     * @return List of Certificate type
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<CertificateDto> getByParameters(SearchQuery searchQuery) {
        List<CertificateDto> list = certificateService.getByParameters(searchQuery);
        certificateHateoasBuilder.buildToEntitiesCollection(list);
        return list;
    }

    /**
     * Adds new Certificate to DB
     * @param certificateDto full certificate entity
     * @return Certificate object
     */

    @RequestMapping(method = RequestMethod.POST)
    public CertificateDto create(@RequestBody @Validated CertificateDto certificateDto) {
        try {
            return certificateService.add(certificateDto);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Deletes a Certificate
     * @param certificateDto certificate object
     * @param id an identifier to perform an operation by
     */

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void removeCertificate(@RequestBody CertificateDto certificateDto, @PathVariable Long id) {
        try {
            certificateService.delete(certificateDto, id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }

    /**
     * Finds Certificate by ID
     * @param id An identifier to perform an operation by
     * @return certificate
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public CertificateDto getCertificate(@PathVariable Long id) {
        CertificateDto certificateDto = certificateService.getById(id);
        certificateHateoasBuilder.buildForMainEntity(certificateDto);
        return certificateDto;
    }

    /**
     * Updates Certificate by entered fields
     * @param certificateDto Certificate object
     * @param id An identifier to perform an operation by
     */

    @RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
    public CertificateDto updateCertificate(@RequestBody CertificateDto certificateDto, @PathVariable Long id) {
        try {
            return certificateService.update(certificateDto, id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
    }
}