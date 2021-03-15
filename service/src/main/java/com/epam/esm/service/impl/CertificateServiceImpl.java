package com.epam.esm.service.impl;

import com.epam.esm.dao.*;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.service.exception.ServiceException;
import com.epam.esm.service.exception.certificate.CertificateAlreadyExistsException;
import com.epam.esm.service.exception.certificate.CertificateNotFoundException;
import com.epam.esm.service.exception.certificate.UnableToDeleteCertificateException;
import com.epam.esm.service.utils.SearchQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class CertificateServiceImpl implements CertificateService {

    private CertificateDao certificateDao;
    private TagDao tagDao;
    private Converter<Certificate, CertificateDto> certificateConverter;
    private Converter<Tag, TagDto> tagConverter;
    private CustomCertificateRepository customCertificateRepository;

    @Autowired
    public CertificateServiceImpl(CertificateDao certificateDao,
                                  TagDao tagDao,
                                  Converter<Certificate, CertificateDto> certificateConverter,
                                  Converter<Tag, TagDto> tagConverter,
                                  CustomCertificateRepository customCertificateRepository) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
        this.certificateConverter = certificateConverter;
        this.tagConverter = tagConverter;
        this.customCertificateRepository = customCertificateRepository;
    }

    public CertificateServiceImpl() {
    }

    @Override
    public List<CertificateDto> getByParameters(SearchQuery searchQuery) throws ServiceException {
        Pageable pageable = SearchQueryUtil.getPage(searchQuery);
        return certificateConverter.convertObjectListToDto(
                customCertificateRepository.findAll(new CertificateSpecification<>(searchQuery), pageable).toList());
    }

    @Transactional
    @Override
    public CertificateDto add(CertificateDto certificateDto) throws ServiceException, DaoException {

            if(certificateDao.getByName(certificateDto.getName())!= null){
                throw new CertificateAlreadyExistsException("Certificate already exists");
            }
        if (certificateDto.getTags()!=null){
            for (TagDto tagDto: certificateDto.getTags()){
                    if (tagDao.getByName(tagDto.getName())==null){
                        Tag tag = tagConverter.convertDtoToObject(tagDto);
                        tagDao.add(tag);
                    }
            }
        }
            Certificate certificate = certificateConverter.convertDtoToObject(certificateDto);
            return certificateConverter.convertObjectToDto(certificateDao.add(certificate));
    }

    @Transactional
    @Override
    public void delete(CertificateDto certificateDto, Long id) throws ServiceException {
            if(certificateDao.getById(id) == null) {
                throw new CertificateNotFoundException("Certificate not found");
            }

            final Certificate quantity = certificateDao.getById(id);
            if(quantity == null) {
                throw new UnableToDeleteCertificateException("Unable to delete certificate");
            }
            certificateDao.getById(id);
            certificateDto.setId(id);
            Certificate certificate = certificateConverter.convertDtoToObject(certificateDto);
            certificateConverter.convertObjectToDto(certificateDao.delete(certificate));
    }

    @Override
    public CertificateDto getById(Long id) throws ServiceException {
            Certificate certificate = certificateDao.getById(id);
            if(certificate == null) {
                throw new CertificateNotFoundException("Certificate not found");
            }
            return certificateConverter.convertObjectToDto(certificateDao.getById(id));
    }

    @Transactional
    @Override
    public CertificateDto update(CertificateDto certificateDto, Long id) throws ServiceException {
            if (certificateDao.getById(id) == null)
                throw new CertificateNotFoundException("Certificate was not found");
            certificateDto.setId(id);
            Certificate certificate = certificateConverter.convertDtoToObject(certificateDto);
            return certificateConverter.convertObjectToDto(certificateDao.edit(certificate));
    }

    @Override
    public List<CertificateDto> getCertificateByTag(List<Long> tagList) throws ServiceException {
             return certificateConverter.convertObjectListToDto(certificateDao.getCertificateByTag(tagList));
    }

    @Override
    public CertificateDto getByName(String name) throws ServiceException {
            Certificate certificate = certificateDao.getByName(name);
            if(certificate == null) {
                throw new CertificateNotFoundException("Certificate not found");
            }
            return certificateConverter.convertObjectToDto(certificateDao.getByName(name));
    }
}
