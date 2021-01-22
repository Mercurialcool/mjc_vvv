package com.epam.esm.dao;

import com.epam.esm.model.Tag;

import java.util.List;

public interface TagDao {
    List<Tag> findAll();
    Tag add(Tag tag);
    void delete(Tag tag);
    Tag getByName(String name);
}
