package com.epam.esm.service.converter.impl;

import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.model.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter implements Converter<Order, OrderDto> {

    @Autowired
    private ModelMapper dtoRouter;

    @Override
    public Order convertDtoToObject(OrderDto dto) {
        Order order = new Order();
        dtoRouter.map(dto, order);
        return order;
    }

    @Override
    public OrderDto convertObjectToDto(Order entity) {
        OrderDto orderDto = new OrderDto();
        dtoRouter.map(entity, orderDto);
        return orderDto;
    }

    @Override
    public List<Order> convertDtoToObjectList(List<OrderDto> dtoList) {
        return dtoList.stream().map(this::convertDtoToObject).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> convertObjectListToDto(List<Order> objectList) {
        return objectList.stream().map(this::convertObjectToDto).collect(Collectors.toList());
    }
}
