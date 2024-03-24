package com.davidpoza.demo.models;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

  @Transactional
  public Article findByUrl(String url);
}
