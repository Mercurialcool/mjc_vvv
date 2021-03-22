package com.epam.esm.dao;

import com.epam.esm.model.Tag;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.*;

public class CertificateSpecification<T> implements Specification<T> {

    private SearchQuery searchQuery;
    private List<Predicate> conditions;
    private List<Predicate> predicates;
    private Map<String, String> jpaTagParameters;

    public CertificateSpecification(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    private void createNewListIfNeeded() {
        this.conditions = Optional.ofNullable(conditions).orElseGet(ArrayList::new);
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
            createNewListIfNeeded();
            Subquery<String> subQueryForTags = criteriaQuery.subquery(String.class);
            Root<Tag> subRoot = subQueryForTags.from(Tag.class);
            Join<Object, Object> subJoin = subRoot.join("certificates");
            subQueryForTags.select(subRoot.get("name")).where(criteriaBuilder.equal(subJoin.get("id"), root.get("id")));

            for (int i = 0; i < searchQuery.getTags().size(); i++) {
                String parameter = "tag" + i;
                jpaTagParameters.putIfAbsent(parameter, searchQuery.getTags().get(i));
                ParameterExpression<String> jpaParameter = criteriaBuilder.parameter(String.class, parameter);
                CriteriaBuilder.In<String> inClause = criteriaBuilder.in(jpaParameter).value(subQueryForTags);
                conditions.add(inClause);
            }
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
