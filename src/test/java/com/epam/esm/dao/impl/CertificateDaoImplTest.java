package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.RowMapTagProvider;
import com.epam.esm.dao.exception.DaoException;
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

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class CertificateDaoImplTest {
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private CertificateDao certificateDao;

    private List<Certificate> certificateList;

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

        List<Certificate> all = new CertificateDaoImpl().getByParameters(params);

        Assert.assertEquals(Collections.emptyList(), all);
    }

    }