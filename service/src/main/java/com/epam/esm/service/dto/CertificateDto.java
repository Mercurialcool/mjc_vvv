package com.epam.esm.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Validated
@JsonRootName(value = "certificate")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CertificateDto extends EntityDto<Long, CertificateDto> implements Serializable {
    private static final long serialVersionUID = 1144649892569462913L;

    @NotBlank(message = "Field 'name' cannot be empty")
    @Pattern(regexp = "[\\-0-9A-Za-zА-Яа-яЁё ]{3,30}", message = "Wrong input! Name can contain only characters" +
            "and has to be from 3 to 30 in length")
    private String name;

    @NotBlank(message = "Field 'description cannot be empty'")
    @Pattern(regexp = "[\\-,.:!?0-9A-Za-zА-Яа-яЁё ]{3,250}", message = "Wrong input! Description can contain only characters" +
            "and has to be from 3 to 250 in length")
    private String description;

    @NotNull(message = "Field 'price' cannot be empty")
    @DecimalMin(value = "1.0", message = "Invalid input! Price has to be 1.0 and above")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal price;

    @NotNull(message = "Field 'duration' cannot be empty")
    @Positive(message = "Field 'duration' cannot be 0 or negative")
    private Integer duration;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant createDate = Instant.now();

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant lastUpdateDate = Instant.now();

    private Set<TagDto> tags;

    public CertificateDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Instant lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Set<TagDto> getTags() {
        return tags;
    }

    public void setTags(Set<TagDto> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CertificateDto)) return false;
        if (!super.equals(o)) return false;

        CertificateDto that = (CertificateDto) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (lastUpdateDate != null ? !lastUpdateDate.equals(that.lastUpdateDate) : that.lastUpdateDate != null)
            return false;
        return tags != null ? tags.equals(that.tags) : that.tags == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (lastUpdateDate != null ? lastUpdateDate.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CertificateDto{");
        sb.append("name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", duration=").append(duration);
        sb.append(", createDate=").append(createDate);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }
}
