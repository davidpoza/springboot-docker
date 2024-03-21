package com.davidpoza.demo.models;

import java.sql.Date;


import com.davidpoza.demo.enums.State;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Job {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  private Date created_at;
  private Date finished_at;
  private Date updated_at;
  private State state;

  // @OneToOne(mappedBy = "job", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // private Article article;

  public Long getId() {
    return id;
  }
  public Date getCreated_at() {
    return created_at;
  }
  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }
  public Date getFinished_at() {
    return finished_at;
  }
  public void setFinished_at(Date finished_at) {
    this.finished_at = finished_at;
  }
  public Date getUpdated_at() {
    return updated_at;
  }
  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }
  public State getState() {
    return state;
  }
  public void setState(State state) {
    this.state = state;
  }
  // public Article getArticle() {
  //   return article;
  // }
  // public void setArticle(Article article) {
  //   this.article = article;
  // }


}
