package com.epam.esm.service.converter;

import java.util.List;

public interface Converter<O, D> {
    /**
     * Converts Data transfer object to an entity type
     * @param dto
     * @return object of a needed type
     */
    O convertDtoToObject(D dto);

    /**
     * Converts an entity type to data transfer object
     * @param entity
     * @return object of a needed type
     */
    D convertObjectToDto(O entity);

    /**
     * Converts Data transfer object to an entity type
     * @param dtoList
     * @return object list of a needed type
     */
    List<O> convertDtoToObjectList(List<D> dtoList);


    /**
     * Converts an entity type to data transfer object
     * @param objectList
     * @return object list of a needed type
     */
    List<D> convertObjectListToDto(List<O> objectList);
}
