package com.epam.esm.dao.impl;

import com.epam.esm.config.TestConfig;
import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.exception.DaoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class CertificateDaoImplTest {

    @Autowired
    private CertificateDao certificateDao;

    @Test
    public void testGetByName() throws DaoException {
        certificateDao.getById(1L);
    }







































//    private CertificateDaoImpl dao;
//    private JdbcTemplate jdbcTemplate;
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
//
//    @BeforeEach
//    void setup() {
//        dao = new CertificateDaoImpl();
//        jdbcTemplate = Mockito.mock(JdbcTemplate.class);
//        namedParameterJdbcTemplate = Mockito.mock(NamedParameterJdbcTemplate.class);
//        dao.setJdbcTemplate(jdbcTemplate);
//        dao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
//    }
//
//    @Test
//    void allCertificates() {
//        Mockito.when(jdbcTemplate.query(
//                Matchers.eq(GET_ALL_CERTIFICATES),
//                Matchers.any(RowMapCertificateProvider.class))).thenReturn(Collections.emptyList());
//        List<Certificate> all = dao.getAll();
//        Assert.assertEquals(Collections.emptyList(), all);
//    }
//
//    @Test
//    void add() throws DaoException {
//        Certificate certificate = new Certificate();
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        Map<String, Object> map = new HashMap<>();
//        map.put("", 1);
//        keyHolder.getKeyList().add(map);
//        dao.setKeyHolder(keyHolder);
//        dao.add(certificate);
//        Mockito.verify(namedParameterJdbcTemplate).update(
//                Matchers.eq(ADD_NEW_CERTIFICATE),
//                Matchers.any(BeanPropertySqlParameterSource.class),
//                Matchers.eq(keyHolder),
//                Matchers.eq(new String[]{"id"})
//        );
//
//        Assert.assertEquals(1L, certificate.getId().longValue());
//    }
//
//    @Test
//    void delete() throws DaoException {
//        Certificate certificate = new Certificate();
//        certificate.setId(1L);
//        dao.delete(certificate);
//        Mockito.verify(jdbcTemplate).update(REMOVE_CERTIFICATE, 1L);
//    }
//
//    @Test
//    void edit() throws DaoException {
//        Certificate certificate = new Certificate();
//        certificate.setId(1L);
//        dao.edit(certificate);
//        Mockito.verify(namedParameterJdbcTemplate).update(
//                Matchers.eq("UPDATE gift_certificate SET last_update_date =:lastUpdateDate where id =:id"),
//                Matchers.any(BeanPropertySqlParameterSource.class));
//    }
//
//    @Test
//    void getById() throws DaoException {
//        Certificate certificate = new Certificate();
//        certificate.setId(1L);
//        Mockito.when(jdbcTemplate.queryForObject(
//                Matchers.eq(GET_CERTIFICATE_BY_ID),
//                Matchers.eq(new Object[]{1L}),
//                Matchers.any(BeanPropertyRowMapper.class)
//        )).thenReturn(certificate);
//        Certificate result = dao.getById(1L);
//        Assert.assertEquals(certificate, result);
//    }
}