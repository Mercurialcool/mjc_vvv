package com.epam.esm.controller;


import com.epam.esm.dao.SearchQuery;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDto> getAll(SearchQuery searchQuery) {
        List<OrderDto> orders = orderService.findAll(searchQuery);
        return orders;

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OrderDto get(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }
}
