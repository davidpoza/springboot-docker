package com.davidpoza.demo.models;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticlesRepository extends JpaRepository<Article, Long> {

}
