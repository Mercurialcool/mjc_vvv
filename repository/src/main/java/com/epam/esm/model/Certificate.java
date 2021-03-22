package com.epam.esm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"tags"})
@ToString(callSuper = true, exclude = {"tags"})
@Table(name = "gift_certificate")
@Inheritance(strategy = InheritanceType.JOINED)
public class Certificate extends com.epam.esm.model.Entity<Long> implements Serializable {

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

}
