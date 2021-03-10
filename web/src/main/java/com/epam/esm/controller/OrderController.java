package com.epam.esm.controller;


import com.epam.esm.dao.SearchQuery;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.util.impl.OrderHateoasBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    OrderHateoasBuilder orderHateoasBuilder;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDto> getAll(SearchQuery searchQuery) {
        List<OrderDto> orders = orderService.findAll(searchQuery);
        orderHateoasBuilder.buildToEntitiesCollection(orders);
        return orders;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OrderDto get(@PathVariable Long id) {
        OrderDto orderDto = orderService.findOrderById(id);
        orderHateoasBuilder.buildForMainEntity(orderDto);
        return orderDto;
    }
}
