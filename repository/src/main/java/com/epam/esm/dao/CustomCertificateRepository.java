package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface CustomCertificateRepository extends PagingAndSortingRepository<Certificate, Long>, JpaSpecificationExecutor<Certificate> {

}
