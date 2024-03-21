package com.davidpoza.demo.dto;

import java.util.Date;


public class ArticleDTO {
  private long id;
  private String url;
  private String title;
  private Date publishedAt;
  private String fullContent;
  private String summaryContent;

  public ArticleDTO(String url, String title, Date publishedAt, String summaryContent) {
    this.url = url;
    this.title = title;
    this.publishedAt = publishedAt;
    this.summaryContent = summaryContent;
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
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


}
