package com.epam.esm.dao;

import com.epam.esm.dao.exception.DaoException;
import com.epam.esm.model.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getAll() throws DaoException;
    Order add(Order order) throws DaoException;
    Order getById(Long id) throws DaoException;
}
