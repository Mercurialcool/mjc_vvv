package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapTagProvider implements RowMapper<Tag> {

    public static final String PROVIDER_ID = "id";
    public static final String PROVIDER_NAME = "name";

    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {
        Tag tag = new Tag();
        tag.setId(resultSet.getLong(PROVIDER_ID));
        tag.setName(resultSet.getString(PROVIDER_NAME));
        return tag;
    }
}
