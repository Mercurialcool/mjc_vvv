package com.epam.esm.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

public class Certificate implements Serializable {

   private Long id;
   private String  name;
   private String description;
   private double price;
   private int duration;
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   private Timestamp createDate;
   @JsonFormat(shape = JsonFormat.Shape.STRING)
   private Timestamp lastUpdateDate;

   private Set<Tag> tags;

   public Certificate() {
   }

   public Set<Tag> getTags() {
      return tags;
   }

   public void setTags(Set<Tag> tags) {
      this.tags = tags;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   public int getDuration() {
      return duration;
   }

   public void setDuration(int duration) {
      this.duration = duration;
   }

   public Timestamp getCreateDate() {
      return createDate;
   }

   public void setCreateDate(Timestamp createDate) {
      this.createDate = createDate;
   }

   public Timestamp getLastUpdateDate() {
      return lastUpdateDate;
   }

   public void setLastUpdateDate(Timestamp lastUpdateDate) {
      this.lastUpdateDate = lastUpdateDate;
   }
}
