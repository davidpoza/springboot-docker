package com.davidpoza.demo.services;

import java.util.ArrayList;

import com.davidpoza.demo.dto.ArticleDTO;


public interface FeedService {
  public ArrayList<ArticleDTO> extractArticles();
}
