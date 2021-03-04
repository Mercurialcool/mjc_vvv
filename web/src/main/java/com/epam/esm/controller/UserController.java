package com.epam.esm.controller;


import com.epam.esm.dao.SearchQuery;
import com.epam.esm.model.User;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private OrderService orderService;

    @Autowired
    public UserController(UserService userService,
                          OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAll(SearchQuery searchQuery) {
        try {
            return userService.getAll(searchQuery);
        } catch (ServiceException e) {
            throw e;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserDto getUser(@PathVariable Long id) {
            return userService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/orders")
    public OrderDto createOrder(@RequestBody @Valid OrderDto orderDto, @PathVariable Long id) {
        orderDto.getUser().setId(id);
        return orderService.create(orderDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/orders")
    public List<OrderDto> findOrders(@PathVariable Long id, SearchQuery searchQuery) {
        List<OrderDto> orders = orderService.findOrdersByUserId(id, searchQuery);
        return orders;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/orders/{orderId}")
    public OrderDto findOrder(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
        OrderDto order = orderService.findOrderIfUserOwnsIt(orderId, userId);
        return order;
    }
}
