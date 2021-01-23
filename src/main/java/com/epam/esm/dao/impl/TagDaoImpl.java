package com.epam.esm.dao.impl;

import com.epam.esm.dao.DaoException;
import com.epam.esm.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import com.epam.esm.model.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.epam.esm.dao.SqlProvider.*;


@Repository
@Transactional(readOnly = true)
public class TagDaoImpl implements TagDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Tag> getAll() {
        return jdbcTemplate.query(GET_ALL_TAGS, (rs, rowNum) -> {
            Tag tag = new Tag();
            tag.setId(rs.getInt(1));
            tag.setName(rs.getString(2));
            return tag;
        });
    }

    @Transactional
    @Override
    public Tag add(Tag tag) {
        jdbcTemplate.update(ADD_NEW_TAG,
                tag.getId(),
                tag.getName());
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

}
