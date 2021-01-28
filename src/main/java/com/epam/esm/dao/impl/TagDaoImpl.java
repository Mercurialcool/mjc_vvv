package com.epam.esm.dao.impl;

import com.epam.esm.dao.DaoException;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.epam.esm.model.Tag;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.epam.esm.dao.RowMapProvider.*;
import static com.epam.esm.dao.SqlProvider.*;


@Repository
@Transactional(readOnly = true)
public class TagDaoImpl implements TagDao, RowMapper<Tag> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query(GET_ALL_TAGS, new TagDaoImpl());
    }

    @Override
    public List<Tag> getByParameters(MultiValueMap<String, String> params) throws DaoException {
        return null;
    }

    @Transactional
    @Override
    public Tag add(Tag tag) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            PreparedStatementCreator preparedStatementCreator = con -> {
                PreparedStatement preparedStatement = con.prepareStatement(ADD_NEW_TAG, PreparedStatement.RETURN_GENERATED_KEYS);
                int col = 1;
                preparedStatement.setString(col++, tag.getName());
                return preparedStatement;
            };
            jdbcTemplate.update(preparedStatementCreator, keyHolder);

            tag.setId(keyHolder.getKey().longValue());
        }
        catch (DataAccessException e){
            throw new DaoException(e);
        }
        return tag;
    }

    @Transactional
    @Override
    public void delete(Tag tag) {
        jdbcTemplate.update(REMOVE_CERTIFICATE, tag.getId());
    }

    @Override
    public void edit(Tag tag) throws DaoException {
        try {
            jdbcTemplate.update(EDIT_TAG,
                    tag.getId(),
                    tag.getName());
        } catch (DataAccessException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Tag getByName(String name) {
        try {
            return jdbcTemplate.queryForObject(GET_TAG_BY_ID, new Object[]{name},
                    new BeanPropertyRowMapper<>(Tag.class));
        } catch (EmptyResultDataAccessException e){
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
