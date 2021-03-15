package com.epam.esm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "gift_certificate")
@Inheritance(strategy = InheritanceType.JOINED)
public class Certificate extends com.epam.esm.model.Entity implements Serializable {

   @Column(name = "name")
   private String name;

   @Column(name = "description")
   private String description;

   @Column(name = "price")
   private BigDecimal price;

   @Column(name = "duration")
   private Integer duration;

   @Column(name = "create_date")
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   private Instant createDate = Instant.now();

   @Column(name = "last_update_date")
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   private Instant lastUpdateDate = Instant.now();

   @ManyToMany
   @JoinTable(name = "certificates_tags",
           joinColumns = @JoinColumn(name = "gift_certificate_id", referencedColumnName = "id"),
           inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
   private Set<Tag> tags;

   public Certificate() {
   }

   public Set<Tag> getTags() {
      return tags;
   }

   public void setTags(Set<Tag> tags) {
      this.tags = tags;
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

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Certificate)) return false;
      if (!super.equals(o)) return false;

      Certificate that = (Certificate) o;

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
      final StringBuilder sb = new StringBuilder("Certificate{");
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
