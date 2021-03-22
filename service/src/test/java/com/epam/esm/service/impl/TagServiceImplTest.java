//package com.epam.esm.service.impl;
//
//import com.epam.esm.dao.TagDao;
//import com.epam.esm.model.Tag;
//import com.epam.esm.service.TagService;
//import com.epam.esm.service.converter.Converter;
//import com.epam.esm.service.converter.impl.TagConverter;
//import com.epam.esm.service.dto.TagDto;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//import static org.mockito.Matchers.*;
//import static org.mockito.Mockito.only;
//import static org.mockito.Mockito.times;
//
//@ExtendWith(MockitoExtension.class)
//class TagServiceImplTest {
//
//    @Mock
//    private TagDao tagDao;
//
//    @Mock
//    private TagDto tagDto;
//
//    @Mock
//    private TagService tagService;
//
//    @Mock
//    private Converter<Tag, TagDto> tagConverter;
//
//    @Test
//    void getByParameters() {
//    }
//
//    @Test
//    void add() {
//        final String name = "tag1";
//
//        TagDto tagDto = new TagDto();
//        tagDto.setName(name);
//
//        Tag tag = new Tag();
//        tag.setName(name);
//
//        Tag createdTag = new Tag();
//        createdTag.setId(1L);
//        createdTag.setName(name);
//
//        TagDto createdTagDto = new TagDto();
//        createdTagDto.setId(1L);
//        createdTagDto.setName(name);
//
//        given(tagConverter.convertDtoToObject(tagDto))
//                .willReturn(tag);
//        given(tagDao.getByName(tag.getName()))
//                .willReturn(null);
//        given(tagDao.add(tag))
//                .willReturn(createdTag);
//        given(tagConverter.convertObjectToDto(createdTag))
//                .willReturn(createdTagDto);
//
//        TagDto actual = tagService.add(tagDto);
//
//        assertNotNull(actual);
//        assertEquals(createdTagDto, actual);
//
//        then(tagConverter)
//                .should(times(1))
//                .convertDtoToObject(any(TagDto.class));
//
//        then(tagDao)
//                .should(times(1))
//                .getByName(anyString());
//
//        then(tagDao)
//                .should(times(1))
//                .add(any(Tag.class));
//
//        then(tagConverter)
//                .should(times(1))
//                .convertObjectToDto(any(Tag.class));
//    }
//
//    @Test
//    void delete() {
//        final Long id = 1L;
//        final String name = "tag1";
//
//        Tag tag = new Tag();
//        tag.setId(1L);
//        tag.setName(name);
//
//        given(tagDao.getById(id)).willReturn(tag);
//        willDoNothing().given(tagDao).delete(tag);
//
//        tagService.delete(tagDto,  id);
//
//        then(tagDao)
//                .should(times(1))
//                .getById(anyLong());
//
//        then(tagDao)
//                .should(times(1))
//                .delete(any(Tag.class));
//    }
//
//    @Test
//    void getByName() {
//    }
//
//    @Test
//    void getById() {
//        final Long id = 1L;
//        final String name = "tag1";
//
//        Tag tag = new Tag();
//        tag.setId(id);
//        tag.setName(name);
//
//        TagDto tagDto = new TagDto();
//        tagDto.setId(id);
//        tagDto.setName(name);
//
//        given(tagDao.getById(id)).willReturn(tag);
//        given(tagConverter.convertObjectToDto(tag)).willReturn(tagDto);
//
//        TagDto actual = tagService.getById(id);
//
//        assertEquals(tagDto, actual);
//
//        then(tagDao)
//                .should(only())
//                .getById(anyLong());
//
//        then(tagConverter)
//                .should(only())
//                .convertObjectToDto(any(Tag.class));
//    }
//
//    @Test
//    void getMostFrequentTag() {
//    }
//}