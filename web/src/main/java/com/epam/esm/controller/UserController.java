package com.epam.esm.controller;


import com.epam.esm.dao.SearchQuery;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.UserService;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.util.impl.OrderHateoasBuilder;
import com.epam.esm.util.impl.UserHateoasBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private OrderService orderService;

    @Autowired
    UserHateoasBuilder userHateoasBuilder;

    @Autowired
    OrderHateoasBuilder orderHateoasBuilder;

    @Autowired
    public UserController(UserService userService,
                          OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAll(SearchQuery searchQuery) {
        List<UserDto> users = userService.getAll(searchQuery);
        userHateoasBuilder.buildToEntitiesCollection(users);
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserDto getUser(@PathVariable Long id) {
        UserDto userDto = userService.getById(id);
        userHateoasBuilder.buildForMainEntity(userDto);
        return userDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/orders")
    public OrderDto createOrder(@RequestBody @Valid OrderDto orderDto, @PathVariable Long id) {
        orderDto.getUser().setId(id);
        return orderService.create(orderDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/orders")
    public List<OrderDto> findOrders(@PathVariable Long id, SearchQuery searchQuery) {
        List<OrderDto> orders = orderService.findOrdersByUserId(id, searchQuery);
        orderHateoasBuilder.buildToEntitiesCollection(orders);
        return orders;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{userId}/orders/{orderId}")
    public OrderDto findOrder(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
        OrderDto order = orderService.findOrderIfUserOwnsIt(orderId, userId);
        orderHateoasBuilder.buildForMainEntity(order);
        return order;
    }
}
