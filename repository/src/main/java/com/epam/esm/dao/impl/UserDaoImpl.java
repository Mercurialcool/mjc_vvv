package com.epam.esm.dao.impl;

import com.epam.esm.dao.CustomRepository;
import com.epam.esm.dao.RowMapUserProvider;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.User;
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

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;
    private CustomRepository customRepository;

    @Autowired
    public UserDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                       JdbcTemplate jdbcTemplate,
                       CustomRepository customRepository) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
        this.customRepository = customRepository;
    }

    public static final String GET_ALL_USERS = "SELECT * FROM users";
    public static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE name = ?";
    public static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    public static final String ADD_NEW_USER = "INSERT INTO users (name,surname,phone_nubmer) values(:name,:surname,:phone_number)";

    @Override
    public List<User> getUserByName(String name) throws DaoException {
        List<User> users = jdbcTemplate.query(GET_USER_BY_NAME, new RowMapUserProvider());
        return users;
    }

    @Override
    public User getById(Long id) throws DaoException {
        Optional<User> optional = customRepository.findById(id);
        return optional.orElse(null);
        //        try {
//            return jdbcTemplate.queryForObject(GET_USER_BY_ID, new Object[]{id},
//                    new BeanPropertyRowMapper<>(User.class));
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> userList = jdbcTemplate.query(GET_ALL_USERS, new RowMapUserProvider());
        return userList;
    }

    @Override
    public User create(User user) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            final BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(user);
            namedParameterJdbcTemplate.update(ADD_NEW_USER, paramSource, keyHolder, new String[]{"id"});
            user.setId(keyHolder.getKey().longValue());
        }
        catch (DataAccessException e){
            throw new DaoException(e);
        }
        return user;
    }

}
