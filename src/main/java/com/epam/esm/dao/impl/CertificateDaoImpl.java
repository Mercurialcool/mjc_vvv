package com.epam.esm.dao.impl;



import com.epam.esm.dao.CertificateDao;
import com.epam.esm.dao.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.dao.RowMapProvider.*;
import static com.epam.esm.dao.SqlProvider.*;

@Repository
@Transactional(readOnly = true)
public class CertificateDaoImpl implements CertificateDao, RowMapper<Certificate> {

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
        return jdbcTemplate.query(GET_ALL_CERTIFICATES, new CertificateDaoImpl());

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
        return jdbcTemplate.query(stringBuilder.toString(), new CertificateDaoImpl());
    }

    @Transactional
    @Override
    public Certificate add(Certificate certificate) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            PreparedStatementCreator preparedStatementCreator = con -> {
                PreparedStatement preparedStatement = con.prepareStatement(ADD_NEW_CERTIFICATE, PreparedStatement.RETURN_GENERATED_KEYS);
                int col = 1;
                preparedStatement.setString(col++, certificate.getName());
                preparedStatement.setString(col++, certificate.getDescription());
                preparedStatement.setDouble(col++, certificate.getPrice());
                preparedStatement.setInt(col++, certificate.getDuration());
                preparedStatement.setTimestamp(col++, certificate.getCreateDate());
                preparedStatement.setTimestamp(col++, certificate.getLastUpdateDate());
                return preparedStatement;
            };
            jdbcTemplate.update(preparedStatementCreator, keyHolder);

            certificate.setId(keyHolder.getKey().longValue());
        }
        catch (DataAccessException e){
            throw new DaoException(e);
    }
            return certificate;
        }

    @Transactional
    @Override
    public void delete(Certificate certificate) throws DaoException {
        try {
            jdbcTemplate.update(REMOVE_CERTIFICATE, certificate.getId());
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Transactional
    @Override
    public void edit(Certificate certificate) throws DaoException {
        certificate.setLastUpdateDate(new Timestamp(System.currentTimeMillis()));
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
        return jdbcTemplate.query(GET_CERTIFICATES_BY_IDS, new Object[]{idList}, new CertificateDaoImpl());
    }

    @Override
    public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(resultSet.getLong(PROVIDER_ID));
        certificate.setName(resultSet.getString(PROVIDER_NAME));
        certificate.setDescription(resultSet.getString(PROVIDER_DESCRIPTION));
        certificate.setPrice(resultSet.getDouble(PROVIDER_PRICE));
        certificate.setDuration(resultSet.getInt(PROVIDER_DURATION));
        certificate.setCreateDate(resultSet.getTimestamp(PROVIDER_CREATE_DATE));
        certificate.setLastUpdateDate(resultSet.getTimestamp(PROVIDER_LAST_UPDATE_DATE));
        return certificate;
    }
}