package com.davidpoza.demo.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bulletin {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private Date publishedAt;
  private Date generationStartedAt;
  private Date generationCompletedAt;

  public Long getId() {
    return id;
  }

  public Date getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(Date publishedAt) {
    this.publishedAt = publishedAt;
  }

  public Date getGenerationStartedAt() {
    return generationStartedAt;
  }

  public void setGenerationStartedAt(Date generationStartedAt) {
    this.generationStartedAt = generationStartedAt;
  }

  public Date getGenerationCompletedAt() {
    return generationCompletedAt;
  }

  public void setGenerationCompletedAt(Date generationCompletedAt) {
    this.generationCompletedAt = generationCompletedAt;
  }


}
