package com.epam.esm.service.converter.impl;

import com.epam.esm.service.converter.Converter;
import com.epam.esm.service.dto.TagDto;
import com.epam.esm.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConverter implements Converter<Tag, TagDto> {

    @Autowired
    private ModelMapper dtoRouter;

    @Override
    public Tag convertDtoToObject(TagDto dto) {
        Tag tag = new Tag();
        dtoRouter.map(dto, tag);
        return tag;
    }

    @Override
    public TagDto convertObjectToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        dtoRouter.map(tag, tagDto);
        return tagDto;
    }

    @Override
    public List<Tag> convertDtoToObjectList(List<TagDto> dtoList) {
        return dtoList.stream().map(this::convertDtoToObject).collect(Collectors.toList());
    }

    @Override
    public List<TagDto> convertObjectListToDto(List<Tag> objectList) {
        return objectList.stream().map(this::convertObjectToDto).collect(Collectors.toList());
    }
}
