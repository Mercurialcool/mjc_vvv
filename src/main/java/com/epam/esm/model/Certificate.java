package com.epam.esm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

public class Certificate extends Entity implements Serializable {

   private String name;
   private String description;
   private Double price;
   private Integer duration;
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   private Instant createDate = Instant.now();
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   private Instant lastUpdateDate = Instant.now();

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

   public Double getPrice() {
      return price;
   }

   public void setPrice(Double price) {
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
