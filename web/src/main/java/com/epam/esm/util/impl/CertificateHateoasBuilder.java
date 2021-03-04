package com.epam.esm.util.impl;

import com.epam.esm.service.dto.EntityDto;
import com.epam.esm.util.HateoasBuilder;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CertificateHateoasBuilder implements HateoasBuilder {

    private final TagHateoasBuilder tagHateoasBuilder;

    public CertificateHateoasBuilder(TagHateoasBuilder tagHateoasBuilder) {
        this.tagHateoasBuilder = tagHateoasBuilder;
    }

    @Override
    public void buildSelfReference(EntityDto dto) {
    }

    @Override
    public void buildForMainEntity(EntityDto dto) {

    }

    @Override
    public void buildForMinorEntity(EntityDto dto) {

    }

    @Override
    public CollectionModel buildToEntitiesCollection(List listDto) {
        return null;
    }
}
