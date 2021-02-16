package com.epam.esm.controller;


import com.epam.esm.service.dto.OrderDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @RequestMapping(method = RequestMethod.GET)
    public List<OrderDto> getAll() {
        return null;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public OrderDto get(@PathVariable Long id) {
        return null;
    }
}
