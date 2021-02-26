package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Entity;
import com.epam.esm.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.util.Optional;

public interface CustomRepository extends CrudRepository<User, Long> {

    public static final String GET_BY_ID = "SELECT * FROM gift_certificate WHERE id = ?";//todo ask



}
