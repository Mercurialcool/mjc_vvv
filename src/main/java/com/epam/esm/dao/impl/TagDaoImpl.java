package com.epam.esm.dao.impl;

import com.epam.esm.dao.RowMapCertificateProvider;
import com.epam.esm.dao.RowMapTagProvider;
import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.service.exception.certificate.CertificateNotFoundException;
import com.epam.esm.service.exception.tag.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.epam.esm.model.Tag;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static com.epam.esm.dao.RowMapCertificateProvider.*;


@Repository
public class TagDaoImpl implements TagDao, RowMapper<Tag> {

    public static final String REMOVE_TAG = "DELETE FROM tag WHERE id = ?";
    public static final String GET_ALL_TAGS = "SELECT * FROM tag";
    public static final String ADD_NEW_TAG = "INSERT INTO tag (name) values(:name)";
    public static final String GET_TAG_BY_ID = "SELECT * FROM tag WHERE id = ?";
    public static final String GET_TAG_BY_NAME = "SELECT * FROM tag WHERE name = ?";
    public static final String EDIT_TAG = "UPDATE tag SET name = ? WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query(GET_ALL_TAGS, new TagDaoImpl());
    }

    @Override
    public List<Tag> getByParameters(MultiValueMap<String, String> params) throws DaoException {
        if (CollectionUtils.isEmpty(params))
            throw new DaoException();
        final StringBuilder stringBuilder = new StringBuilder("SELECT * FROM tag WHERE ");
        if (Optional.ofNullable(params.get("name")).isPresent()) {
            stringBuilder
                    .append(" name LIKE '%")
                    .append(params.getFirst("name"))
                    .append("%'");
        }
        List<Tag> tagList = jdbcTemplate.query(stringBuilder.toString(), new RowMapTagProvider());
        if (tagList.isEmpty()) {
            throw new TagNotFoundException("Not found");
        }
        return tagList;
    }

    @Override
    public Tag add(Tag tag) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            final BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(tag);
            namedParameterJdbcTemplate.update(ADD_NEW_TAG, paramSource, keyHolder, new String[]{"id"});
            tag.setId(keyHolder.getKey().longValue());
        }
        catch (DataAccessException e){
            throw new DaoException(e);
        }
        return tag;
    }

    @Override
    public Certificate delete(Tag tag) {
        jdbcTemplate.update(REMOVE_TAG, tag.getId());
        return null;
    }

    @Override
    public Certificate edit(Tag tag) throws DaoException {
        try {
            jdbcTemplate.update(EDIT_TAG,
                    tag.getId(),
                    tag.getName());
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Tag getByName(String name) {
        try {
            return jdbcTemplate.queryForObject(GET_TAG_BY_NAME, new Object[]{name},
                    new BeanPropertyRowMapper<>(Tag.class));
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Tag getById(Long id) throws DaoException {
        try {
            return jdbcTemplate.queryForObject(GET_TAG_BY_ID, new Object[]{id},
                    new BeanPropertyRowMapper<>(Tag.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getLong(PROVIDER_ID));
        tag.setName(resultSet.getString(PROVIDER_NAME));
        return tag;
    }

}
