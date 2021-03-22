//package com.epam.esm.dao.impl;
//
//import com.epam.esm.dao.RowMapTagProvider;
//import com.epam.esm.dao.exception.DaoException;
//import com.epam.esm.model.Tag;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import java.util.Collections;
//import java.util.List;
//
//import static com.epam.esm.dao.impl.TagDaoImpl.GET_ALL_TAGS;
//
//class TagDaoImplTest {
//    private TagDaoImpl dao;
//    private JdbcTemplate jdbcTemplate;
//
//    @BeforeEach
//    void setup() {
//        dao = new TagDaoImpl();
//        jdbcTemplate = Mockito.mock(JdbcTemplate.class);
//       // dao.setJdbcTemplate(jdbcTemplate);
//    }
//
//    @Test
//    void allTags() {
//        Mockito.when(jdbcTemplate.query(Mockito.eq(GET_ALL_TAGS), Mockito.any(TagDaoImpl.class)))
//                .thenReturn(Collections.emptyList());
//
//        List<Tag> all = dao.getAll();
//
//        Assert.assertEquals(Collections.emptyList(), all);
//    }
//
//    @Test
//    void getByParameters() throws DaoException {
//        Mockito.when(jdbcTemplate.query(Mockito.eq("SELECT * FROM gift_certificate WHERE  name LIKE '%name%'"), Mockito.any(RowMapTagProvider.class)))
//                .thenReturn(Collections.emptyList());
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.add("name", "name");
//        params.add("description", "description");
//
//        List<Tag> all = dao.getByParameters(params);
//
//        Assert.assertEquals(Collections.emptyList(), all);
//    }
//}