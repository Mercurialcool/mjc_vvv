package com.epam.esm.dao;

import com.epam.esm.model.Certificate;
import com.epam.esm.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CertificateSpecification<T> implements Specification<T> {

    @Autowired
    private EntityManager entityManager;

    private SearchQuery searchQuery;
    private List<Predicate> predicates;

    public CertificateSpecification(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        predicates = new ArrayList<>();
        if (searchQuery.getName() != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + searchQuery.getName() + "%"));
        }
        if (searchQuery.getDescription() != null) {
            predicates.add(criteriaBuilder.like(root.get("description"), "%" + searchQuery.getDescription() + "%"));
        }
        if (searchQuery.getSortByName() != null) {
            criteriaQuery.orderBy(SortEnum.valueOf(searchQuery.getSortByName()).getValue() ?
                    criteriaBuilder.asc(root.get("name")):criteriaBuilder.desc(root.get("name")));
        }
        if (searchQuery.getTags() != null) {
            Expression<Collection<String>> tagsName = root.get("tagsName");
            Predicate hasTags = criteriaBuilder.isMember("tagsName", tagsName);

            criteriaQuery.where(hasTags);
            List<?> hasTagsList = entityManager.createQuery(criteriaQuery).getResultList();
        }
        switch (predicates.size()) {
            case 0:
                return criteriaBuilder.and();
            case 1:
                return predicates.get(0);
            default:
                Predicate finalPredicate = criteriaBuilder.and(predicates.get(0), predicates.get(1));
                if (predicates.size() >= 3) {
                    for (int i = 2; i < predicates.size(); i++) {
                        finalPredicate = criteriaBuilder.and(finalPredicate, predicates.get(i));
                    }
                }
                return finalPredicate;
        }
    }
}
