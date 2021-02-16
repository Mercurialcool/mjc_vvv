package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapCertificateProvider implements RowMapper<Certificate> {

    public static final String PROVIDER_ID = "id";
    public static final String PROVIDER_NAME = "name";
    public static final String PROVIDER_DESCRIPTION = "description";
    public static final String PROVIDER_PRICE = "price";
    public static final String PROVIDER_DURATION= "duration";
    public static final String PROVIDER_CREATE_DATE = "create_date";
    public static final String PROVIDER_LAST_UPDATE_DATE = "last_update_date";

    @Override
    public Certificate mapRow(ResultSet resultSet, int i) throws SQLException {
            Certificate certificate = new Certificate();
            certificate.setId(resultSet.getLong(PROVIDER_ID));
            certificate.setName(resultSet.getString(PROVIDER_NAME));
            certificate.setDescription(resultSet.getString(PROVIDER_DESCRIPTION));
            certificate.setPrice(resultSet.getDouble(PROVIDER_PRICE));
            certificate.setDuration(resultSet.getInt(PROVIDER_DURATION));
            certificate.setCreateDate(resultSet.getTimestamp(PROVIDER_CREATE_DATE) == null ? null : resultSet.getTimestamp(PROVIDER_CREATE_DATE).toInstant());
            certificate.setLastUpdateDate(resultSet.getTimestamp(PROVIDER_LAST_UPDATE_DATE) == null ? null : resultSet.getTimestamp(PROVIDER_LAST_UPDATE_DATE).toInstant());
            return certificate;
        }
    }

