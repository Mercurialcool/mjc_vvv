package com.epam.esm.dao;

import com.epam.esm.model.Tag;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomTagRepository extends PagingAndSortingRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {

}
