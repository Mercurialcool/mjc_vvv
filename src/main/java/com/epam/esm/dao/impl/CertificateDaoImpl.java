package com.epam.esm.dao.impl;



import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.RowMapCertificateProvider;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.sql.*;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    public static final String GET_ALL_CERTIFICATES = "SELECT * FROM gift_certificate";
    public static final String GET_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id = ?";
    public static final String GET_CERTIFICATE_BY_NAME = "SELECT * FROM gift_certificate WHERE name = ?";
    public static final String ADD_NEW_CERTIFICATE = "INSERT INTO gift_certificate (name,description,price,duration,create_date,last_update_date) values(:name,:description,:price,:duration,:createDate,:lastUpdateDate)";
    public static final String REMOVE_CERTIFICATE = "DELETE FROM gift_certificate WHERE id = ?";
    public static final String GET_CERTIFICATES_BY_IDS = "SELECT * FROM gift_certificate WHERE id IN (?)";
    public static final String GET_CERTIFICATES_BY_TAG_ID = "SELECT gift_certificate_id FROM tag_has_gift_certificate WHERE tag_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private List<Long> getCertificateIdsByTagId(Long tagId){
        return jdbcTemplate.query(GET_CERTIFICATES_BY_TAG_ID, new Object[] {tagId},
                (rs, rowNum) -> rs.getLong(1));
    }

    @Override
    public List<Certificate> getAll() {
        return jdbcTemplate.query(GET_ALL_CERTIFICATES, new RowMapCertificateProvider());

    }

    @Override
    public List<Certificate> getByParameters(MultiValueMap<String, String> params) throws DaoException {
        if (CollectionUtils.isEmpty(params))
            throw new DaoException();
        final StringBuilder stringBuilder = new StringBuilder("SELECT * FROM gift_certificate WHERE ");
        if (Optional.ofNullable(params.get("name")).isPresent() && Optional.ofNullable(params.get("description")).isPresent()) {
            stringBuilder
                    .append(" name LIKE '%")
                    .append(params.getFirst("name"))
                    .append("%'")
                    .append(" and ")
                    .append("description LIKE '%")
                    .append(params.getFirst("description"))
                    .append("%'");
        }

        if (Optional.ofNullable(params.get("name")).isPresent()) {
            stringBuilder
                    .append(" name LIKE '%")
                    .append(params.getFirst("name"))
                    .append("%'");
        }

        if (Optional.ofNullable(params.get("description")).isPresent()) {
            stringBuilder
                    .append(" description LIKE '%")
                    .append(params.getFirst("description"))
                    .append("%'");
        }
        return jdbcTemplate.query(stringBuilder.toString(), new RowMapCertificateProvider());
    }

    @Override
    public Certificate add(Certificate certificate) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            final BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(certificate);
            namedParameterJdbcTemplate.update(ADD_NEW_CERTIFICATE, paramSource, keyHolder, new String[]{"id"});
            certificate.setId(keyHolder.getKey().longValue());
        }
        catch (DataAccessException e){
            throw new DaoException(e);
    }
            return certificate;
        }

    @Override
    public Certificate delete(Certificate certificate) throws DaoException {
        try {
            jdbcTemplate.update(REMOVE_CERTIFICATE, certificate.getId());
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
        return certificate;
    }

    @Override
    public Certificate edit(Certificate certificate) throws DaoException {
        certificate.setLastUpdateDate(Instant.now());
        final BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(certificate);
        paramSource.registerSqlType("lastUpdateDate", Types.TIMESTAMP);
        StringBuilder stringBuilder = new StringBuilder("UPDATE gift_certificate SET ")
                .append("last_update_date =:lastUpdateDate");
        Optional.ofNullable(certificate.getName()).ifPresent(x-> stringBuilder.append(", name =:name"));
        Optional.ofNullable(certificate.getDescription()).ifPresent(x-> stringBuilder.append(", description =:description"));
        Optional.ofNullable(certificate.getPrice()).ifPresent(x-> stringBuilder.append(", price =:price"));
        Optional.ofNullable(certificate.getPrice()).ifPresent(x-> stringBuilder.append(", duration =:duration"));
        stringBuilder.append(" where id =:id");
        this.namedParameterJdbcTemplate.update(stringBuilder.toString(), paramSource);
        return certificate;
    }

    @Override
    public Certificate getByName(String name) {
        try {
            return jdbcTemplate.queryForObject(GET_CERTIFICATE_BY_NAME, new Object[]{name},
                    new BeanPropertyRowMapper<>(Certificate.class));
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Certificate getById(Long id) throws DaoException {
        try {
            return jdbcTemplate.queryForObject(GET_CERTIFICATE_BY_ID, new Object[]{id},
                    new BeanPropertyRowMapper<>(Certificate.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Certificate> getCertificateByTag(Tag tagName) throws DaoException {
        List<Long> ids = getCertificateIdsByTagId(tagName.getId());
        List<String> idsString = ids.stream().map((id) -> id.toString()).collect(Collectors.toList());
        String idList = String.join(",", idsString);
        return jdbcTemplate.query(GET_CERTIFICATES_BY_IDS, new Object[]{idList}, new RowMapCertificateProvider());
    }
}