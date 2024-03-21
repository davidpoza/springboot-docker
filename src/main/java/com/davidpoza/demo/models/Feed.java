package com.davidpoza.demo.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Feed {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String url;
  private String title;

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


}
