package com.epam.esm.util.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.OrderController;
import com.epam.esm.service.dto.EntityDto;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.util.HateoasBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class OrderHateoasBuilder implements HateoasBuilder<Long, OrderDto> {

    private void buildGetAndPostHateoas(RepresentationModel representationModel) {
        representationModel.add(linkTo(GiftCertificateController.class).withRel("get all"));
        representationModel.add(linkTo(GiftCertificateController.class).withRel("create new"));
    }

    @Override
    public void buildSelfReference(OrderDto dto) {
        dto.add(linkTo(OrderController.class).slash(dto.getId()).withSelfRel());
    }

    @Override
    public void buildForMainEntity(OrderDto dto) {
        buildForMinorEntity(dto);
        buildGetAndPostHateoas(dto);
    }

    @Override
    public void buildForMinorEntity(OrderDto dto) {
        buildSelfReference(dto);
    }

    @Override
    public CollectionModel<OrderDto> buildToEntitiesCollection(List<OrderDto> listDto) {
        listDto.forEach(this::buildSelfReference);
        Link self = linkTo(OrderController.class).withSelfRel();
        CollectionModel<OrderDto> collectionModel = CollectionModel.of(listDto, self);
        buildGetAndPostHateoas(collectionModel);
        return collectionModel;
    }
}
