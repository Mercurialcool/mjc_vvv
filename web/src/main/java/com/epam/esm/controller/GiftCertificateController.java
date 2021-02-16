package com.epam.esm.controller;

import com.epam.esm.model.Certificate;
import com.epam.esm.service.dto.CertificateDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private static final Logger logger = LogManager.getLogger(GiftCertificateController.class);
    private CertificateService certificateService;

    @Autowired
    public GiftCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * Extracts MultiValueMap to get proper parameters for search query
     * @param
     * @return List of Certificate type
     */

    @RequestMapping(method = RequestMethod.GET)
    public List<CertificateDto> getByParameters(@RequestParam(required = false) MultiValueMap<String, String> params) {
        try {
            return certificateService.getByParameters(params);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
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
        try {
            return certificateService.getById(id);
        } catch (ServiceException e) {
            logger.error(e);
            throw e;
        }
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