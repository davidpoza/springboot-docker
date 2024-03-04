package com.davidpoza.demo.models;


import java.util.HashSet;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tag {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private UUID id;

  @ManyToMany(mappedBy = "tags")
  private HashSet<CuredArticle> curedArticles = new HashSet<>();

  public UUID getId() {
    return id;
  }


}
