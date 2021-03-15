package com.epam.esm.service.impl;

import com.epam.esm.dao.*;
import com.epam.esm.model.Order;
import com.epam.esm.model.User;
import com.epam.esm.service.OrderService;
import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.service.exception.order.OrderAlreadyExistsException;
import com.epam.esm.service.exception.order.OrderNotFoundException;
import com.epam.esm.service.utils.SearchQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private CertificateDao certificateDao;
    private UserDao userDao;
    private Converter<Order, OrderDto> orderConverter;
    private CustomOrderRepository customOrderRepository;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao,
                            CertificateDao certificateDao,
                            UserDao userDao,
                            Converter<Order, OrderDto> orderConverter,
                            CustomOrderRepository customOrderRepository) {
        this.orderDao = orderDao;
        this.certificateDao = certificateDao;
        this.userDao = userDao;
        this.orderConverter = orderConverter;
        this.customOrderRepository = customOrderRepository;
    }

    @Override
    public OrderDto findOrderById(Long id) {
        Order order = orderDao.getById(id);
        return orderConverter.convertObjectToDto(orderDao.getById(id));
    }

    @Transactional
    public OrderDto create(OrderDto orderDto) {
        if (orderDto.getUser().getId() == null)
            throw new OrderAlreadyExistsException("Order already exists");
        User user = userDao.getById(orderDto.getUser().getId());
        if (user == null)
            throw new OrderNotFoundException("Order not found");
        Order order = new Order(Instant.now(), 1, user);
        return orderConverter.convertObjectToDto(customOrderRepository.save(order));
    }

//    private Set<OrderCondition> findOrderConditions(OrderDto orderDto) {
//        return orderDto.getOrderConditions().stream()
//                .map(orderCondition -> {Long certificateId = orderCondition.getCertificate()
//                .getId();
//              Certificate certificate = certificateDao.getById(certificateId);
//                    if (certificate == null) {
//                        throw new CertificateNotFoundException("Not found");
//                    }
//                    return certificate;
//                })
//                .map(certificate -> new OrderCondition(certificate.getPrice(), orderDto, certificate))
//                .collect(Collectors.toSet());
//    }

    @Override
    public OrderDto findOrderIfUserOwnsIt(Long userId, Long orderId) {
        User user = userDao.getById(userId);
        Order order = customOrderRepository.findById(orderId).get();
        if (!order.getUser().getId().equals(user.getId())) {
            try {
                throw new IllegalAccessException();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return orderConverter.convertObjectToDto(order);
    }

    @Override
    public List<OrderDto> findOrdersByUserId(Long userId, SearchQuery searchQuery) {
        User user = userDao.getById(userId);
        if(user == null) {
            throw new OrderNotFoundException("Not found");
        }
        return orderConverter.convertObjectListToDto(customOrderRepository.findAllByUser(user, SearchQueryUtil.getPage(searchQuery)).toList());
    }

    @Override
    public List<OrderDto> findAll(SearchQuery searchQuery) {
        return orderConverter.convertObjectListToDto(customOrderRepository.findAll(SearchQueryUtil.getPage(searchQuery)).toList());
    }
}
