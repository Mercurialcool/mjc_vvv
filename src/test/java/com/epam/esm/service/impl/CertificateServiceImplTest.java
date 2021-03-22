//package com.epam.esm.service.impl;
//
//
//import com.epam.esm.dao.CertificateDao;
//import com.epam.esm.model.Certificate;
//import com.epam.esm.model.Tag;
//import com.epam.esm.service.CertificateService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.then;
//import static org.mockito.Matchers.anyString;
//import static org.mockito.Mockito.only;
//import static org.mockito.Mockito.times;
//
//
//@ExtendWith(MockitoExtension.class)
//class CertificateServiceImplTest {
//
//    @Mock
//    private CertificateDao certificateDao;
//
//    @InjectMocks
//    private final CertificateService certificateService = new CertificateServiceImpl();
//
//    private List<Certificate> certificates;
//
//    @BeforeEach
//    void beforeEach() {
//        certificates = new ArrayList<>();
//        for (long i = 1; i <= 4; i++) {
//            Certificate certificate = new Certificate();
//            certificate.setId(1L);
//            certificate.setName("testName" + i);
//            certificate.setDescription("testDescription" + i);
//            certificate.setPrice(10.0 + i);
//            certificate.setDuration((int) (5 + i));
//            certificates.add(certificate);
//        }
//    }
//
//    @Test
//    void getCertificateByTag() {
//        final Tag tag = new Tag();
//        List<Certificate> expected = certificateService.getCertificateByTag(tag);
//        assertEquals(expected, given(certificateDao.getCertificateByTag(tag)));
//        then(certificateDao).should(only()).getCertificateByTag(tag);
//    }
//
//    @Test
//    void getByName() {
//        final Certificate certificate = new Certificate();
//        Certificate expected = certificateService.getByName(certificate.getName());
//        assertEquals(expected, given(certificateDao.getByName(certificate.getName())));
//    }
//
//    @Test
//    void add() {
//        Certificate certificate = new Certificate();
//        certificate.setId(1L);
//        certificate.setName("testName1");
//
//        given(certificateDao.getByName(certificate.getName())).willReturn(null);
//
//
//        Certificate expected = certificateService.add(certificate);
//        assertEquals(expected, given(certificateDao.add(certificate)).willReturn(certificate));
//        then(certificateDao).should(times(1)).getByName(anyString());
//        then(certificateDao).should(times(1)).add(any(Certificate.class));
//    }
//
//
//    @Test
//    void getByParameters() {
//        List<Certificate> expectedList = certificateService.getByParameters(null);
//        assertEquals(expectedList, given(certificateDao.getAll()).willReturn(certificates));
//    }
//
//    @Test
//    void delete() {
//        Certificate certificate = new Certificate();
//        given(certificateDao.getById(certificate.getId())).willReturn(certificate);
//
//        certificateService.delete(certificate, 1L);
//        then(certificateDao).should(times(1)).getById(anyLong());
//        then(certificateDao).should(times(1)).delete(certificate);
//    }
//
//    @Test
//    void update() {
//    }
//}