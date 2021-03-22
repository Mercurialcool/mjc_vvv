//package com.epam.esm.service.impl;
//
//import com.epam.esm.dao.CertificateDao;
//import com.epam.esm.model.Certificate;
//import com.epam.esm.service.CertificateService;
//import com.epam.esm.service.converter.Converter;
//import com.epam.esm.service.dto.CertificateDto;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//import static org.mockito.Matchers.any;
//import static org.mockito.Matchers.anyLong;
//import static org.mockito.Mockito.only;
//import static org.mockito.Mockito.times;
//
//@ExtendWith(MockitoExtension.class)
//class CertificateServiceImplTest {
//
//    @Mock
//    private CertificateDao certificateDao;
//
//    @Mock
//    private CertificateDto certificateDto;
//
//    @Mock
//    private Converter<Certificate, CertificateDto> certificateConverter;
//
//    @InjectMocks
//    private final CertificateService certificateService = new CertificateServiceImpl();
//
//    private static List<Certificate> giftCertificateList;
//    private static List<CertificateDto> giftCertificateDtoList;
//
//    @BeforeAll
//    static void beforeAll() {
//        giftCertificateList = new ArrayList<>();
//        giftCertificateDtoList = new ArrayList<>();
//
//        for (long i = 1; i <= 2; i++) {
//            Certificate giftCertificate = new Certificate();
//            giftCertificate.setId(i);
//            giftCertificate.setName("name" + i);
//
//            CertificateDto giftCertificateDto = new CertificateDto();
//            giftCertificateDto.setId(i);
//            giftCertificateDto.setName("name" + i);
//
//            giftCertificateList.add(giftCertificate);
//            giftCertificateDtoList.add(giftCertificateDto);
//        }
//    }
//
//    @Test
//    void getByParameters() {
//    }
//
//    @Test
//    void add() {
//        CertificateDto certificateDto = giftCertificateDtoList.get(0);
//        Certificate giftCertificate = giftCertificateList.get(0);
//
//        final String name = giftCertificate.getName();
//        Certificate created = giftCertificate;
//        CertificateDto createdDto = certificateDto;
//
//        given(certificateConverter.convertDtoToObject(certificateDto))
//                .willReturn(giftCertificate);
//        given(certificateDao.getByName(name))
//                .willReturn(null);
//        given(certificateDao.add(giftCertificate))
//                .willReturn(created);
//        given(certificateConverter.convertObjectToDto(created))
//                .willReturn(createdDto);
//
//        CertificateDto actual = certificateService.add(certificateDto);
//
//        assertNotNull(actual);
//        assertEquals(createdDto, actual);
//
//        then(certificateConverter)
//                .should(times(1))
//                .convertDtoToObject(any(CertificateDto.class));
//
//        then(certificateDao)
//                .should(times(1))
//                .getByName(name);
//
//        then(certificateDao)
//                .should(times(1))
//                .add(any(Certificate.class));
//
//        then(certificateConverter)
//                .should(times(1))
//                .convertObjectToDto(any(Certificate.class));
//    }
//
//    @Test
//    void delete() {
//        Certificate certificate = giftCertificateList.get(0);
//        final Long id = certificate.getId();
//
//        given(certificateDao.getById(id))
//                .willReturn(certificate);
//        willDoNothing().given(certificateDao)
//                .delete(certificate);
//
//        certificateService.delete(certificateDto, id);
//
//        then(certificateDao)
//                .should(times(1))
//                .getById(anyLong());
//
//        then(certificateDao)
//                .should(times(1))
//                .delete(any(Certificate.class));
//    }
//
//    @Test
//    void getById() {
//        Certificate certificate = giftCertificateList.get(0);
//        CertificateDto giftCertificateDto = giftCertificateDtoList.get(0);
//        final Long id = certificate.getId();
//
//        given(certificateDao.getById(id))
//                .willReturn(certificate);
//        given(certificateConverter.convertObjectToDto(certificate))
//                .willReturn(giftCertificateDto);
//
//        CertificateDto actual = certificateService.getById(id);
//
//        assertNotNull(actual);
//        assertEquals(actual, giftCertificateDto);
//
//        then(certificateDao)
//                .should(only())
//                .getById(anyLong());
//
//        then(certificateConverter)
//                .should(only())
//                .convertObjectToDto(any(Certificate.class));
//    }
//
//    @Test
//    void update() {
//        CertificateDto certificateDto = giftCertificateDtoList.get(0);
//        Certificate certificate = giftCertificateList.get(0);
//
//        final Long id = certificate.getId();
//        Certificate existed = certificate;
//        Certificate updated = certificate;
//
//        given(certificateDao.getById(id))
//                .willReturn(existed);
//        given(certificateDao.edit(certificate))
//                .willReturn(updated);
//
//        CertificateDto actual = certificateService.update(certificateDto, id);
//
//        assertNotNull(actual);
//
//        then(certificateDao)
//                .should(times(1))
//                .getById(anyLong());
//
//        then(certificateDao)
//                .should(times(1))
//                .edit(any(Certificate.class));
//
//        then(certificateConverter)
//                .should(only())
//                .convertObjectToDto(any(Certificate.class));
//    }
//
//    @Test
//    void getCertificateByTag() {
//    }
//
//    @Test
//    void getByName() {
//        Certificate certificate = giftCertificateList.get(0);
//        CertificateDto giftCertificateDto = giftCertificateDtoList.get(0);
//        final String name = certificate.getName();
//
//        given(certificateDao.getByName(name))
//                .willReturn(certificate);
//        given(certificateConverter.convertObjectToDto(certificate))
//                .willReturn(giftCertificateDto);
//
//        CertificateDto actual = certificateService.getByName(name);
//
//        assertNotNull(actual);
//        assertEquals(actual, giftCertificateDto);
//
//        then(certificateDao)
//                .should(only())
//                .getById(anyLong());
//
//        then(certificateConverter)
//                .should(only())
//                .convertObjectToDto(any(Certificate.class));
//    }
//}