package com.epam.esm.util.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.util.HateoasBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class CertificateHateoasBuilder implements HateoasBuilder<Long, CertificateDto> {

    private final TagHateoasBuilder tagHateoasBuilder;

    public CertificateHateoasBuilder(TagHateoasBuilder tagHateoasBuilder) {
        this.tagHateoasBuilder = tagHateoasBuilder;
    }

    private void buildGetAndPostHateoas(RepresentationModel representationModel) {
        representationModel.add(linkTo(GiftCertificateController.class).withRel("get all"));
        representationModel.add(linkTo(GiftCertificateController.class).withRel("create new"));
    }

    @Override
    public void buildSelfReference(CertificateDto dto) {
        dto.add(linkTo(GiftCertificateController.class).slash(dto.getId()).withSelfRel());
    }

    @Override
    public void buildForMainEntity(CertificateDto dto) {
        buildForMinorEntity(dto);
        buildGetAndPostHateoas(dto);
    }

    @Override
    public void buildForMinorEntity(CertificateDto dto) {
        buildSelfReference(dto);
        dto.add(linkTo(GiftCertificateController.class).slash(dto.getId()).withRel("to be updated"));
        dto.add(linkTo(GiftCertificateController.class).slash(dto.getId()).withRel("to be deleted"));
        dto.getTags().forEach(tagHateoasBuilder::buildSelfReference);
    }

    @Override
    public CollectionModel<CertificateDto> buildToEntitiesCollection(List<CertificateDto> listDto) {
        listDto.forEach(this::buildForMinorEntity);
        Link self = linkTo(GiftCertificateController.class).withSelfRel();
        CollectionModel<CertificateDto> collectionModel = CollectionModel.of(listDto, self);
        buildGetAndPostHateoas(collectionModel);
        return collectionModel;
    }
}
