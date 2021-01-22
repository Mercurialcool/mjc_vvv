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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.dao.SqlProvider.*;

@Repository
@Transactional(readOnly = true)
public class CertificateDaoImpl implements CertificateDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Integer> getCertificateIdsByTagId(Integer tagId){
        return jdbcTemplate.query(GET_CERTIFICATES_BY_TAG_ID, new Object[] {tagId},
                (rs, rowNum) -> rs.getInt(1));
    }


    @Override
    public List<Certificate> allCertificates() {
        return jdbcTemplate.query(GET_ALL_CERTIFICATES, (rs, rowNum) -> dbExpression(rs));

    }

    private Certificate dbExpression(ResultSet rs) throws SQLException {
        Certificate certificate = new Certificate();
        certificate.setId(rs.getLong(1));
        certificate.setName(rs.getString(2));
        certificate.setDescription(rs.getString(3));
        certificate.setPrice(rs.getDouble(4));
        certificate.setDuration(rs.getInt(5));
        certificate.setCreateDate(rs.getTimestamp(6));
        certificate.setLastUpdateDate(rs.getTimestamp(7));
        return certificate;
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
        try {
            jdbcTemplate.update(EDIT_CERTIFICATE,
                    certificate.getName(),
                    certificate.getDescription(),
                    certificate.getPrice(),
                    certificate.getDuration(),
                    certificate.getCreateDate(),
                    certificate.getLastUpdateDate(),
                    certificate.getId());
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Certificate getByName(String name) {
        try {
            return jdbcTemplate.queryForObject(GET_CERTIFICATE_BY_ID, new Object[]{name},
                    new BeanPropertyRowMapper<>(Certificate.class));
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Certificate> getCertificateByTag(Tag tagName) throws DaoException {
        List<Integer> ids = getCertificateIdsByTagId(tagName.getId());
        List<String> idsString = ids.stream().map((id) -> id.toString()).collect(Collectors.toList());
        String idList = String.join(",", idsString);
        return jdbcTemplate.query(GET_CERTIFICATES_BY_IDS, new Object[]{idList}, (rs, rowNum) -> dbExpression(rs));
    }

    @Override
    public List<Certificate> searchCertificateByDescription(String description) throws DaoException {
        return jdbcTemplate.query(GET_CERTIFICATE_BY_DESCRIPTION, new Object[]{description}, (rs, rowNum) -> dbExpression(rs));
    }

    @Override
    public List<Certificate> searchCertificateByName(String name) throws DaoException {
        return jdbcTemplate.query(GET_CERTIFICATE_BY_NAME, new Object[]{name}, (rs, rowNum) -> dbExpression(rs));
    }
}