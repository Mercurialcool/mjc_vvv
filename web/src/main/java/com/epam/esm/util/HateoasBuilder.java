package com.epam.esm.util;

import com.epam.esm.service.dto.EntityDto;
import org.springframework.hateoas.CollectionModel;

import java.io.Serializable;
import java.util.List;

public interface HateoasBuilder<ID extends Serializable, T extends EntityDto> {
    void buildSelfReference(T dto);
    void buildForMainEntity(T dto);
    void buildForMinorEntity(T dto);
    CollectionModel<T> buildToEntitiesCollection(List<T> listDto);
}
