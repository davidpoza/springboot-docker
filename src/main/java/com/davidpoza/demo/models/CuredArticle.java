package com.davidpoza.demo.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.UUID;

import org.hibernate.mapping.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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

  @ManyToMany(cascade = { CascadeType.ALL })
  @JoinTable(
      name = "CuredArticles_Tags",
      joinColumns = { @JoinColumn(name = "cured_article_id") },
      inverseJoinColumns = { @JoinColumn(name = "tag_id") }
  )
  HashSet<Tag> tags = new HashSet<>();

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
