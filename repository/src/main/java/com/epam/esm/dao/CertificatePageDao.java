package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CertificatePageDao extends PagingAndSortingRepository<Certificate, Long> {
}
