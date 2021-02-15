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
            if (CollectionUtils.isEmpty(params))
                return certificateDao.getAll();
            return certificateDao.getByParameters(params);
    }

    @Transactional
    @Override
    public Certificate add(Certificate certificate) throws ServiceException, DaoException {

            if(certificateDao.getByName(certificate.getName())!= null){
                throw new CertificateAlreadyExistsException("Certificate already exists");
            }
        if (certificate.getTags()!=null){
            for (Tag tag: certificate.getTags()){
                    if (tagDao.getByName(tag.getName())==null){
                        tagDao.add(tag);
                    }
            }
        }
            return certificateDao.add(certificate);
    }

    @Transactional
    @Override
    public void delete(Certificate certificate, Long id) throws ServiceException {
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
    }

    @Override
    public Certificate getById(Long id) throws ServiceException {
            Certificate certificate = certificateDao.getById(id);
            if(certificate == null) {
                throw new CertificateNotFoundException("Certificate not found");
            }
            return certificateDao.getById(id);
    }

    @Transactional
    @Override
    public Certificate update(Certificate certificate, Long id) throws ServiceException {
            if (certificateDao.getById(id) == null)
                throw new CertificateNotFoundException("Certificate was not found");
            final Certificate quantity = certificateDao.edit(certificate);
            if (quantity == null) {
                throw new UnableToUpdateCertificateException("Unable to update");
            }
            certificate.setId(id);
            return certificateDao.edit(certificate);
    }

    @Override
    public List<Certificate> getCertificateByTag(Tag tag) throws ServiceException {
             return certificateDao.getCertificateByTag(tag);
    }

    @Override
    public Certificate getByName(String name) throws ServiceException {
            Certificate certificate = certificateDao.getByName(name);
            if(certificate == null) {
                throw new CertificateNotFoundException("Certificate not found");
            }
            return certificateDao.getByName(name);
    }
}
