package com.epam.esm.service;

import com.epam.esm.dao.SearchQuery;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    /**
     * Order creation
     * @param orderDto
     * @return Order entity object
     */
    OrderDto create(OrderDto orderDto) throws ServiceException;

    /**
     * Finds order owned by a proper user
     * @param orderId
     * @param userId
     * @return order entity object by search query
     * @throws ServiceException
     */
    OrderDto findOrderIfUserOwnsIt(Long orderId, Long userId) throws ServiceException;

    /**
     * Finds a list of orders which accords to a proper user
     * @param userId
     * @param searchQuery
     * @return list of orders
     * @throws ServiceException
     */
    List<OrderDto> findOrdersByUserId(Long userId, SearchQuery searchQuery) throws ServiceException;

    /**
     * Finds all orders
     * @param searchQuery
     * @return list of all orders
     * @throws ServiceException
     */
    List<OrderDto> findAll(SearchQuery searchQuery);

    OrderDto findOrderById(Long id);
}
