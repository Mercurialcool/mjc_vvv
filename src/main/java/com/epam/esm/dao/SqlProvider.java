package com.epam.esm.dao;

public class SqlProvider {

    public static final String GET_ALL_CERTIFICATES = "SELECT * FROM gift_certificate";
    public static final String GET_CERTIFICATE_BY_ID = "SELECT * FROM gift_certificate WHERE id = ?";
    public static final String ADD_NEW_CERTIFICATE = "INSERT INTO gift_certificate (name,description,price,duration,create_date,last_update_date) values(?,?,?,?,?,?)";
    public static final String REMOVE_CERTIFICATE = "DELETE FROM gift_certificate WHERE id = ?";
    public static final String EDIT_CERTIFICATE = "UPDATE gift_certificate SET name = ?, description = ?, price = ?, duration = ?, create_date = ?, last_update_date = ? WHERE id = ?";
    public static final String GET_CERTIFICATES_BY_IDS = "SELECT * FROM gift_certificate WHERE id IN (?)";
    public static final String GET_CERTIFICATES_BY_TAG_ID = "SELECT gift_certificate_id FROM tag_has_gift_certificate WHERE tag_id = ?";
    public static final String GET_CERTIFICATE_BY_DESCRIPTION = "SELECT * FROM gift_certificate WHERE description = ?";
    public static final String GET_CERTIFICATE_BY_NAME = "SELECT * FROM gift_certificate WHERE name = ?";


    public static final String GET_ALL_TAGS = "SELECT * FROM tag";
    public static final String ADD_NEW_TAG = "INSERT INTO tag values(?,?)";
    public static final String GET_TAG_BY_ID = "SELECT * FROM tag WHERE id = ?";
    public static final String EDIT_TAG = "UPDATE tag SET name = ? WHERE id = ?";
}
