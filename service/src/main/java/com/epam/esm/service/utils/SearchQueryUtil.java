package com.epam.esm.service.utils;

import com.epam.esm.dao.SearchQuery;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class SearchQueryUtil {

    public static Pageable getPage(SearchQuery searchQuery) {
        return PageRequest.of(searchQuery.getPage(),
                searchQuery.getSize() == 0 ? 10 : searchQuery.getSize());
    }

}
