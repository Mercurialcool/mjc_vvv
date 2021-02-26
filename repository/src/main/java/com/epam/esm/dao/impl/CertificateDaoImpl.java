package com.epam.esm.dao.impl;



import com.epam.esm.dao.*;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import com.epam.esm.dao.exception.certificate.DaoCertificateNotFoundException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CertificateDaoImpl implements CertificateDao {

    public static final String GET_ALL_CERTIFICATES = "SELECT * FROM gift_certificate";
    public static final String GET_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id = ?";
    public static final String GET_CERTIFICATE_BY_NAME = "SELECT * FROM gift_certificate WHERE name = ?";
    public static final String ADD_NEW_CERTIFICATE = "INSERT INTO gift_certificate (name,description,price,duration,create_date,last_update_date) values(:name,:description,:price,:duration,:createDateTime,:lastUpdatedTime)";
    public static final String REMOVE_CERTIFICATE = "DELETE FROM gift_certificate WHERE id = ? ";
    public static final String REMOVE_CERTIFICATE_CONSTRAINT = "DELETE FROM certificates_tags WHERE gift_certificate_id = ?";
    public static final String GET_CERTIFICATES_BY_IDS = "SELECT * FROM gift_certificate WHERE id IN (?)";
    public static final String GET_CERTIFICATES_BY_TAG_ID = "SELECT gift_certificate_id FROM certificates_tags WHERE tag_id = ?";
    public static final String GET_TAG_ID_BY_CERTIFICATE = "SELECT tag_id FROM certificates_tags WHERE gift_certificate_id = ?";
    public static final String ADD_NEW_TAG_CERTIFICATE = "INSERT INTO certificates_tags (tag_id, gift_certificate_id) values(:tag_id,:gift_certificate_id)";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private TagDao tagDao;

    @Autowired
    public CertificateDaoImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate, TagDao tagDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.tagDao = tagDao;
    }

    private List<Long> getCertificateIdsByTagId(Long tagId){
        return jdbcTemplate.query(GET_CERTIFICATES_BY_TAG_ID, new Object[] {tagId},
                (rs, rowNum) -> rs.getLong(1));
    }

    private List<Long> getTagIdsByCertificateIds(Long certificateId){
        return jdbcTemplate.query(GET_TAG_ID_BY_CERTIFICATE, new Object[] {certificateId},
                (rs, rowNum) -> rs.getLong(1));
    }
    @Override
    public List<Certificate> getAll() {
        List<Certificate> certificateList = jdbcTemplate.query(GET_ALL_CERTIFICATES, new RowMapCertificateProvider());
        for (Certificate c: certificateList) {
            List<Long> tagsIds = getTagIdsByCertificateIds(c.getId());
            Set<Tag> tagList = tagDao.getTagsByIds(tagsIds);
            c.setTags(tagList);
        }
        return certificateList;
    }

    @Override
    public List<Certificate> getByParameters(SearchQuery searchQuery) throws DaoException {
        if (searchQuery.getName() == null && searchQuery.getDescription() == null && searchQuery.getTags().isEmpty())
            throw new DaoException();
        final StringBuilder stringBuilder = new StringBuilder("SELECT * FROM gift_certificate WHERE ");

        if (Optional.ofNullable(searchQuery.getName()).isPresent()) {
            stringBuilder
                    .append(" name LIKE '%")
                    .append(searchQuery.getName())
                    .append("%'");
        }

        if (Optional.ofNullable(searchQuery.getName()).isPresent() && Optional.ofNullable(searchQuery.getDescription()).isPresent()) {
                stringBuilder.append(" and ");
            }

        if (Optional.ofNullable(searchQuery.getDescription()).isPresent()) {
            stringBuilder
                    .append(" description LIKE '%")
                    .append(searchQuery.getDescription())
                    .append("%'");
        }

        if (Optional.ofNullable(searchQuery.getTags()).isPresent()) {
            stringBuilder.append(" and ");
        }

        if (Optional.ofNullable(searchQuery.getSortByName()).isPresent()) {
            stringBuilder
                    .append(" ORDER BY name ")
                    .append(searchQuery.getSortByName());
        }

         List<Certificate> certificateList = jdbcTemplate.query(stringBuilder.toString(), new RowMapCertificateProvider());
        for (Certificate c: certificateList) {
            List<Long> tagsIds = getTagIdsByCertificateIds(c.getId());
            Set<Tag> tagList = tagDao.getTagsByIds(tagsIds);
            c.setTags(tagList);
        }
        if (certificateList.isEmpty()) {
            throw new DaoCertificateNotFoundException("Not found");
        }
        return certificateList;
    }

    @Override
    public Certificate add(Certificate certificate) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            final CombinedSqlParameterSource paramSource = new CombinedSqlParameterSource(certificate);
            paramSource.addValue("lastUpdatedTime", Timestamp.from(certificate.getLastUpdateDate()));
            paramSource.addValue("createDateTime", Timestamp.from(certificate.getCreateDate()));
                    namedParameterJdbcTemplate.update(ADD_NEW_CERTIFICATE, paramSource, keyHolder, new String[]{"id"});
            certificate.setId(keyHolder.getKey().longValue());
            if (certificate.getTags() != null && !certificate.getTags().isEmpty()) {
                for (Tag t: certificate.getTags()) {
                    Tag foundTag = tagDao.getByName(t.getName());
                    if (foundTag == null) {
                        foundTag = tagDao.add(t);
                    }
                    final CombinedSqlParameterSource parameterSource = new CombinedSqlParameterSource(certificate);
                    parameterSource.addValue("tag_id", foundTag.getId());
                    parameterSource.addValue("gift_certificate_id", certificate.getId());
                    namedParameterJdbcTemplate.update(ADD_NEW_TAG_CERTIFICATE, parameterSource, keyHolder, new String[]{"id"});
                    t.setId(foundTag.getId());
                }
            }
        }
        catch (DataAccessException e){
            throw new DaoException(e);
    }
            return certificate;
    }

    @Override
    public Certificate delete(Certificate certificate) throws DaoException {
        try {
            jdbcTemplate.update(REMOVE_CERTIFICATE_CONSTRAINT, certificate.getId());
            jdbcTemplate.update(REMOVE_CERTIFICATE, certificate.getId());
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
        return certificate;
    }

    @Override
    public Certificate edit(Certificate certificate) throws DaoException {
        certificate.setLastUpdateDate(Instant.now());
        final CombinedSqlParameterSource paramSource = new CombinedSqlParameterSource(certificate);
        paramSource.addValue("lastUpdatedTime", Timestamp.from(certificate.getLastUpdateDate()));
        StringBuilder stringBuilder = new StringBuilder("UPDATE gift_certificate SET ")
                .append("last_update_date =:lastUpdatedTime");
        Optional.ofNullable(certificate.getName()).ifPresent(x-> stringBuilder.append(", name =:name"));
        Optional.ofNullable(certificate.getDescription()).ifPresent(x-> stringBuilder.append(", description =:description"));
        Optional.ofNullable(certificate.getPrice()).ifPresent(x-> stringBuilder.append(", price =:price"));
        Optional.ofNullable(certificate.getDuration()).ifPresent(x-> stringBuilder.append(", duration =:duration"));
        stringBuilder.append(" where id =:id");
        if (certificate.getTags() != null) {
            for (Tag t : certificate.getTags()) {
                Tag foundTag = tagDao.getByName(t.getName());
                if (foundTag == null) {
                    foundTag = tagDao.add(t);
                }
                t.setId(foundTag.getId());
            }
        }
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
        List<Certificate> certificates = jdbcTemplate.query(GET_CERTIFICATES_BY_IDS, new Object[]{idList}, new RowMapCertificateProvider());
        for (Certificate c: certificates) {
            List<Long> tagsIds = getTagIdsByCertificateIds(c.getId());
            Set<Tag> tagList = tagDao.getTagsByIds(tagsIds);
            c.setTags(tagList);
        }
        return certificates;


    }
}