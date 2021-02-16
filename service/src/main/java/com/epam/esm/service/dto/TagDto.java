package com.epam.esm.service.dto;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.io.Serializable;

@JsonRootName(value = "tag")
public class TagDto extends EntityDto implements Serializable {

    private String name;

    public TagDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagDto)) return false;
        if (!super.equals(o)) return false;

        TagDto tagDto = (TagDto) o;

        return name != null ? name.equals(tagDto.name) : tagDto.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagDto{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
