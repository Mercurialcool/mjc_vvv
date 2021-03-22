package com.epam.esm.dao;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUserByName(String name) throws DaoException;
    User getById(Long id) throws DaoException;
    List<User> getAll() throws DaoException;
    User create(User user) throws DaoException;
}
