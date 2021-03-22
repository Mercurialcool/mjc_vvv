package com.epam.esm.dao;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component
@Validated
public class SearchQuery {

    private int page;
    private int size;
    private String description;
    private String name;
    private List<String> tags;
    private String sortByName;

    public SearchQuery() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getSortByName() {
        return sortByName;
    }

    public void setSortByName(String sortByName) {
        this.sortByName = sortByName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchQuery)) return false;

        SearchQuery that = (SearchQuery) o;

        if (page != that.page) return false;
        if (size != that.size) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) return false;
        return sortByName != null ? sortByName.equals(that.sortByName) : that.sortByName == null;
    }

    @Override
    public int hashCode() {
        int result = page;
        result = 31 * result + size;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (sortByName != null ? sortByName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchQuery{");
        sb.append("page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", description='").append(description).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", tags=").append(tags);
        sb.append(", sortByName='").append(sortByName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
