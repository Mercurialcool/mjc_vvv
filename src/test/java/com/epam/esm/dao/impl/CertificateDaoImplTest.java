package com.epam.esm.dao.impl;

import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Certificate;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import static com.epam.esm.dao.SqlProvider.REMOVE_CERTIFICATE;
import static org.junit.jupiter.api.Assertions.*;

class CertificateDaoImplTest {
    private CertificateDaoImpl dao;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setup() {
        dao = new CertificateDaoImpl();
        jdbcTemplate = Mockito.mock(JdbcTemplate.class);
        dao.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    void allCertificates() {
    }

    @Test
    void add() throws DaoException {
        dao.add(new Certificate());
        Mockito.verify(jdbcTemplate).update(Matchers.any(PreparedStatementCreator.class), Matchers.any());
    }

    @Test
    void delete() throws DaoException {
        Certificate certificate = new Certificate();
        certificate.setId(1l);
        dao.delete(certificate);
        Mockito.verify(jdbcTemplate).update(REMOVE_CERTIFICATE, 1);
    }

    @Test
    void edit() {
    }

    @Test
    void getById() {
    }
}