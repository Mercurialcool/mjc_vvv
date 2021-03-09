package com.epam.esm.util.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dao.SearchQuery;
import com.epam.esm.service.dto.EntityDto;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.service.dto.UserDto;
import com.epam.esm.util.HateoasBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoasBuilder implements HateoasBuilder<Long, UserDto> {

    private void buildGetAndPostHateoas(RepresentationModel representationModel) {
        representationModel.add(linkTo(GiftCertificateController.class).withRel("get all"));
    }


    @Override
    public void buildSelfReference(UserDto dto) {
        dto.add(linkTo(UserController.class).slash(dto.getId()).withSelfRel());
    }

    @Override
    public void buildForMainEntity(UserDto dto) {
        buildForMinorEntity(dto);
        buildGetAndPostHateoas(dto);
    }

    @Override
    public void buildForMinorEntity(UserDto dto) {
        buildSelfReference(dto);
        dto.add(linkTo(methodOn(UserController.class).createOrder(new OrderDto(), dto.getId())).withRel("create new"));
        dto.add(linkTo(methodOn(UserController.class).findOrders(dto.getId(), new SearchQuery())).withRel("get all"));
        dto.add(linkTo(methodOn(UserController.class).findOrder(dto.getId(), dto.getId())).withRel("get exact order"));//ask
    }

    @Override
    public CollectionModel<UserDto> buildToEntitiesCollection(List<UserDto> listDto) {
        listDto.forEach(this::buildForMinorEntity);
        Link self = linkTo(UserController.class).withSelfRel();
        CollectionModel<UserDto> collectionModel = CollectionModel.of(listDto, self);
        buildGetAndPostHateoas(collectionModel);
        Link link = linkTo(methodOn(UserController.class).createOrder(new OrderDto(), 777L)).withRel("create new for exact user");//fix
        return collectionModel.add(link);
    }
}
