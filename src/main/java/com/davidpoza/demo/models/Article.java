package com.davidpoza.demo.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Article {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String url;
  private String title;
  private Date publishedAt;
  private String fullContent;
  private String summaryContent;

  @ManyToOne
  @JoinColumn(name = "feed_id")
  private Feed feed;

  @ManyToOne
  @JoinColumn(name = "cured_article_id")
  private CuredArticle curedArticle;


  public Long getId() {
    return id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getPublishedAt() {
    return publishedAt;
  }

  public void setPublishedAt(Date publishedAt) {
    this.publishedAt = publishedAt;
  }

  public String getFullContent() {
    return fullContent;
  }

  public void setFullContent(String fullContent) {
    this.fullContent = fullContent;
  }

  public String getSummaryContent() {
    return summaryContent;
  }

  public void setSummaryContent(String summaryContent) {
    this.summaryContent = summaryContent;
  }

  public Feed getFeed() {
    return feed;
  }

  public void setFeed(Feed feed) {
    this.feed = feed;
  }

  public CuredArticle getCuredArticle() {
    return curedArticle;
  }

  public void setCuredArticle(CuredArticle curedArticle) {
    this.curedArticle = curedArticle;
  }



}
