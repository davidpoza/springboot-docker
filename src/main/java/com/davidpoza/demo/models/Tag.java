package com.davidpoza.demo.models;


import java.util.HashSet;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tag {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;

  @ManyToMany(mappedBy = "tags")
  private HashSet<CuredArticle> curedArticles = new HashSet<>();

  public long getId() {
    return id;
  }


}
