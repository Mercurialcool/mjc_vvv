package com.epam.esm.dao;

import com.epam.esm.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomUserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
}
