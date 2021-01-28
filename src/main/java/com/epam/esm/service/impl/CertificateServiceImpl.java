package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.DaoException;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;


import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {

    private CertificateDao certificateDao;
    private TagDao tagDao;
    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao, TagDao tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }

    @Override
    public List<Certificate> getByParameters(MultiValueMap<String, String> params) throws CertificateServiceException {
        try {
            if (CollectionUtils.isEmpty(params))
                return certificateDao.getAll();
            return certificateDao.getByParameters(params);
        } catch (DaoException e) {
           throw new CertificateServiceException(e);
        }
    }

    @Override
    public Certificate add(Certificate certificate) throws CertificateServiceException, DaoException {
        try {
            if(certificateDao.getByName(certificate.getName())!= null){
                throw new CertificateServiceException();
            }
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
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
           throw new CertificateServiceException(e);
        }
    }

    @Override
    public void delete(Certificate certificate, Long id) throws CertificateServiceException {
        try {
            certificateDao.getById(id);
            certificate.setId(id);
            certificateDao.delete(certificate);
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
    }

    @Override
    public void update(Certificate certificate, Long id) throws CertificateServiceException {
        try {
            if (certificateDao.getById(id) == null)
                return;//todo think what to return
            certificate.setId(id);
            certificateDao.edit(certificate);
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
    }

    @Override
    public List<Certificate> getCertificateByTag(Tag tag) throws CertificateServiceException{
        try {
             return certificateDao.getCertificateByTag(tag);
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
    }

    @Override
    public Certificate getByName(String name) throws CertificateServiceException {
        try {
            return certificateDao.getByName(name);
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
    }
}
