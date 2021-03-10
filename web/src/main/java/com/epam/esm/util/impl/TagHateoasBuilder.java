package com.epam.esm.util.impl;

import com.epam.esm.controller.TagController;
import com.epam.esm.service.converter.impl.TagConverter;
import com.epam.esm.service.dto.EntityDto;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.util.HateoasBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class TagHateoasBuilder implements HateoasBuilder<Long, TagDto> {

    private void buildGetAndPostHateoas(RepresentationModel representationModel) {
        representationModel.add(linkTo(TagController.class).withRel("get all"));
        representationModel.add(linkTo(TagController.class).withRel("create new"));
    }

    @Override
    public void buildSelfReference(TagDto dto) {
        dto.add(linkTo(TagController.class).slash(dto.getId()).withSelfRel());
    }

    @Override
    public void buildForMainEntity(TagDto dto) {
        buildForMinorEntity(dto);
    }

    @Override
    public void buildForMinorEntity(TagDto dto) {
        buildSelfReference(dto);
        dto.add(linkTo(TagController.class).slash(dto.getId()).withRel("to be updated"));
        dto.add(linkTo(TagController.class).slash(dto.getId()).withRel("to be deleted"));
    }

    @Override
    public CollectionModel<TagDto> buildToEntitiesCollection(List<TagDto> listDto) {
        listDto.forEach(this::buildForMinorEntity);
        Link self = linkTo(TagController.class).withSelfRel();
        CollectionModel<TagDto> collectionModel = CollectionModel.of(listDto, self);
        return collectionModel;
    }
}
