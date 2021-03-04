package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomCertificateRepository extends PagingAndSortingRepository<Certificate, Long>, JpaSpecificationExecutor<Certificate> {
//    @Query(value = "SELECT * FROM gift_certificate\n" +
//            "    LEFT OUTER JOIN certificates_tags\n" +
//            "                      ON gift_certificate.id = certificates_tags.gift_certificate_id\n" +
//            "    LEFT OUTER JOIN tag\n" +
//            "                      ON certificates_tags.tag_id = tag.id\n" +
//            "WHERE description LIKE '%P%'\n" +
//            "\n")
// Page<Certificate> findAll(CertificateSpecification certificateSpecification, Pageable pageable);
}
