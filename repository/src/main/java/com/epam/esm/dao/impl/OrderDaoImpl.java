package com.epam.esm.dao.impl;

import com.epam.esm.dao.CombinedSqlParameterSource;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Certificate;
import com.epam.esm.model.Order;
import com.epam.esm.model.Tag;
import com.epam.esm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static com.epam.esm.dao.RowMapOrderProvider.*;

@Repository
public class OrderDaoImpl implements OrderDao, RowMapper<Order> {

    public static final String GET_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";
    public static final String ADD_NEW_ORDER = "INSERT INTO orders (date_of_issue,quantity) values(:date_of_issue,:quantity)";
    public static final String GET_BY_USER = "SELECT * FROM orders WHERE id = ?";//todo fix

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                        JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAll() throws DaoException {
        return null;
    }

    @Override
    public Order add(Order order) throws DaoException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try {
                final CombinedSqlParameterSource parameterSource = new CombinedSqlParameterSource(order);
                parameterSource.addValue("dateOfIssue", Timestamp.from(order.getDateOfIssue()));
                namedParameterJdbcTemplate.update(ADD_NEW_ORDER, parameterSource ,keyHolder, new String[]{"id"});
                order.setId(keyHolder.getKey().longValue());
//            final BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(order);
//            namedParameterJdbcTemplate.update(ADD_NEW_ORDER, paramSource, keyHolder, new String[]{"id"});
//            order.setId(keyHolder.getKey().longValue());
        }
        catch (DataAccessException e){
            throw new DaoException(e);
        }
        return order;
    }

    @Override
    public Order getById(Long id) throws DaoException {
        try {
            return jdbcTemplate.queryForObject(GET_ORDER_BY_ID, new Object[]{id},
                    new BeanPropertyRowMapper<>(Order.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(PROVIDER_ID));
        order.setDateOfIssue(resultSet.getTimestamp(PROVIDER_CREATE_DATE) == null ? null : resultSet.getTimestamp(PROVIDER_CREATE_DATE).toInstant());
        order.setQuantity(resultSet.getFloat(PROVIDER_QUANTITY));
        order.setUser(resultSet.getObject(PROVIDER_USER_ID, User.class));//todo ask
        return order;
    }
}
