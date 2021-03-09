package com.epam.esm.service.dto;

import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

public abstract class EntityDto<ID, T extends EntityDto<ID, T>> extends RepresentationModel<T> implements Serializable {

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityDto)) return false;

        EntityDto entityDto = (EntityDto) o;

        return id != null ? id.equals(entityDto.id) : entityDto.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EntityDto{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
