package com.epam.esm.dao;

import com.epam.esm.model.User;
import org.springframework.data.repository.CrudRepository;

public interface CustomRepository extends CrudRepository<User, Long> {
}
