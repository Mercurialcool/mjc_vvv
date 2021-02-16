package com.epam.esm.dao;

import com.epam.esm.model.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomRepository<T extends Entity> extends CrudRepository<T, Long> {
}
