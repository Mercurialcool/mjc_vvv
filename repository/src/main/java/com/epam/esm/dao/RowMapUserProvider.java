package com.epam.esm.dao;

import com.epam.esm.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapUserProvider implements RowMapper<User> {

    public static final String PROVIDER_ID = "id";
    public static final String PROVIDER_NAME = "name";
    public static final String PROVIDER_SURNAME = "surname";
    public static final String PROVIDER_PHONE_NUMBER = "phone_number";

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(PROVIDER_ID));
        user.setName(resultSet.getString(PROVIDER_NAME));
        user.setSurname(resultSet.getString(PROVIDER_SURNAME));
        user.setPhoneNumber(resultSet.getString(PROVIDER_PHONE_NUMBER));
        return user;
    }
}
