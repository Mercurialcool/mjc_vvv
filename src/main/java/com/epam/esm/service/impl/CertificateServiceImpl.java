package com.epam.esm.service.impl;

import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.DaoException;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.CertificateServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
    public List<Certificate> allCertificates() throws CertificateServiceException {
        try {
            return certificateDao.allCertificates();
        } catch (DaoException e) {
           throw new CertificateServiceException(e);
        }
    }

    @Override
    public Certificate add(Certificate certificate) throws CertificateServiceException{
        try {
            if(certificateDao.getByName(certificate.getName())!= null){
                throw new CertificateServiceException();
            }
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
        if (certificate.getTags()!=null){
            for (Tag tag: certificate.getTags()){
                if (tagDao.getByName(tag.getName())==null){
                    tagDao.add(tag);
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
    public boolean delete(Certificate certificate) throws CertificateServiceException {
        try {
            certificateDao.delete(certificate);
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
        return false;
    }

    @Override
    public void update(Certificate certificate) throws CertificateServiceException {
        try {
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
    public List<Certificate> searchCertificateByDescription(String description)throws CertificateServiceException{
        try {
            return certificateDao.searchCertificateByDescription(description);
        } catch (DaoException e) {
            throw new CertificateServiceException(e);
        }
    }

    @Override
    public List<Certificate> searchCertificateByName(String name) throws CertificateServiceException{
        try {
            return certificateDao.searchCertificateByName(name);
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
