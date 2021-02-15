package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.RowMapTagProvider;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class CertificateDaoImplTest {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private CertificateDao certificateDao;
    
    @Autowired
    private TagDao tagDao;

    private List<Certificate> certificateList;
    private Set<Certificate> certificateSet;

    @BeforeEach
    void getCertificates() {
        jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        namedParameterJdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
        certificateList = certificateDao.getAll();
        assertNotNull(certificateList);
        assertNotEquals(0, certificateList.size());
    }

    @Test
    void getById() {
        final long id = certificateList.get(0).getId();
        Certificate actual = certificateDao.getById(id);
        assertNotNull(actual);
    }

    @Test
    void getByName() {
        final String name = certificateList.get(0).getName();
        Certificate actual = certificateDao.getByName(name);
        assertNotNull(actual);
    }

    @Test
    void updateByParams() {
        Mockito.when(jdbcTemplate.query(Mockito.eq("SELECT * FROM gift_certificate WHERE  name LIKE '%name%'"), Mockito.any(RowMapTagProvider.class)))
                .thenReturn(Collections.emptyList());
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "name");
        params.add("description", "description");

       // List<Certificate> all = new CertificateDaoImpl().getByParameters(params);

        //Assert.assertEquals(Collections.emptyList(), all);
    }

    @Test
    void delete() {
        Certificate certificate = new Certificate();
        final long id = certificateList.get(0).getId();
        Certificate was = certificateDao.getById(id);
        Certificate quant = certificateDao.delete(certificate);
        Certificate actual = certificateDao.getById(id);
        assertNotNull(was);
        assertEquals(1, quant);
        assertNull(actual);
    }

    @Test
    void add() {
        Certificate certificate = new Certificate();
        certificate.setName("testName");
        certificate.setDescription("testDescription");
        certificate.setPrice(10.0);
        certificate.setDuration(120);
        certificate.setCreateDate(Instant.now());

        Certificate notExisted = certificateDao.getByName(certificate.getName());
        Certificate toBeCreated = certificateDao.add(certificate);
        Certificate actual = certificateDao.getByName(certificate.getName());

        assertNull(notExisted);
        assertNotNull(toBeCreated);
        assertEquals(actual, certificate);
    }

    @Test
    void getAllByTagName() {
        final Tag tagName = new Tag();
        List<Certificate> actual = certificateDao.getCertificateByTag(tagName);
        Set<Certificate> expected = certificateSet.stream().filter(certificate -> {
            return certificate.getTags().stream().anyMatch(tag -> tag.getName().equals(tagName));
        }).collect(Collectors.toSet());
        assertEquals(expected, actual);
    }

//    @Test
//    void getByParameters() {
//        MultiValueMap<String, String> params = null;
//        final Tag tagName = new Tag(tagDao.getByName(tag.getName()));
//        final String queryPart = "tnt";
//        List<Certificate> actual = certificateDao.getByParameters(params);
//        List<Certificate> expected = certificateSet.stream().filter(certificate ->  {
//            return (certificate.getName().contains(queryPart)) || certificate.getDescription().contains(queryPart)
//                    && certificate.getTags().stream().anyMatch(tag -> tag.getName().equals(tagName));
//        }).collect(Collectors.toList());
//        assertEquals(expected, actual);
//    }
}