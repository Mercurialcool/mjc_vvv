package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomCertificateRepository extends PagingAndSortingRepository<Certificate, Long> {
    //@Query(value = "SELECT * FROM gift_certificates WHERE description LIKE %?1% and name LIKE %?2%  ")
    Page<Certificate> findAllCertificatesByDescriptionContainingAndNameContaining(String description, String name,  Pageable pageable);
}
