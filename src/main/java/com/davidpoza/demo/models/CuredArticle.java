package com.davidpoza.demo.models;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CuredArticle {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private UUID id;

  private String title;
  private String content;
  private Date publishedAt;

  @ManyToOne
  @JoinColumn(name = "bulletin_id")
  private Bulletin bulletin;


  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(Date publishedAt) {
    this.publishedAt = publishedAt;
  }

  public Bulletin getBulletin() {
    return bulletin;
  }

  public void setBulletin(Bulletin bulletin) {
    this.bulletin = bulletin;
  }


}
