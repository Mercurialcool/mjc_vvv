package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.exception.certificate.CertificateAlreadyExistsException;
import com.epam.esm.service.exception.certificate.CertificateNotFoundException;
import com.epam.esm.service.exception.certificate.UnableToDeleteCertificateException;
import com.epam.esm.service.exception.certificate.UnableToUpdateCertificateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class CertificateServiceImpl implements CertificateService {

    private CertificateDao certificateDao;
    private TagDao tagDao;
    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao, TagDao tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }

    public CertificateServiceImpl() {
    }

    @Override
    public List<Certificate> getByParameters(MultiValueMap<String, String> params) throws ServiceException {
        try {
            if (CollectionUtils.isEmpty(params))
                return certificateDao.getAll();
            return certificateDao.getByParameters(params);
        } catch (DaoException e) {
           throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public Certificate add(Certificate certificate) throws ServiceException, DaoException {
        try {
            if(certificateDao.getByName(certificate.getName())!= null){
                throw new CertificateAlreadyExistsException("Certificate already exists");
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        if (certificate.getTags()!=null){
            for (Tag tag: certificate.getTags()){
                try {
                    if (tagDao.getByName(tag.getName())==null){
                        tagDao.add(tag);
                    }
                } catch (DataAccessException e) {
                    throw new DaoException(e);
                }
            }
        }
        try {
            return certificateDao.add(certificate);
        } catch (DaoException e) {
           throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Certificate certificate, Long id) throws ServiceException {
        try {
            if(certificateDao.getById(id) == null) {
                throw new CertificateNotFoundException("Certificate not found");
            }

            final Certificate quantity = certificateDao.getById(id);
            if(quantity == null) {
                throw new UnableToDeleteCertificateException("Unable to delete certificate");
            }
            certificateDao.getById(id);
            certificate.setId(id);
            certificateDao.delete(certificate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Transactional
    @Override
    public void update(Certificate certificate, Long id) throws ServiceException {
        try {
            if (certificateDao.getById(id) == null)
                throw new CertificateNotFoundException("Certificate was not found");
            final Certificate quantity = certificateDao.edit(certificate);
            if (quantity == null) {
                throw new UnableToUpdateCertificateException("Unable to update");
            }
            certificate.setId(id);
            certificateDao.edit(certificate);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Certificate> getCertificateByTag(Tag tag) throws ServiceException {
        try {
             return certificateDao.getCertificateByTag(tag);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Certificate getByName(String name) throws ServiceException {
        try {
            Certificate certificate = certificateDao.getByName(name);
            if(certificate == null) {
                throw new CertificateNotFoundException("Certificate not found");
            }
            return certificateDao.getByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
