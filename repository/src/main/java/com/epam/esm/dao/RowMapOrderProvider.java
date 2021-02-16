package com.epam.esm.dao;

import com.epam.esm.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapOrderProvider implements RowMapper<Order> {

    public static final String PROVIDER_ID = "id";
    public static final String PROVIDER_CREATE_DATE = "date_of_issue";
    public static final String PROVIDER_QUANTITY = "date_of_issue";
    public static final String PROVIDER_USER_ID = "user_id";


    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong(PROVIDER_ID));
        order.setDateOfIssue(resultSet.getTimestamp(PROVIDER_CREATE_DATE) == null ? null : resultSet.getTimestamp(PROVIDER_CREATE_DATE).toInstant());
        order.setQuantity(resultSet.getFloat(PROVIDER_QUANTITY));
        //order.setUser(resultSet.get(PROVIDER_USER_ID));
        return order;
    }
}
