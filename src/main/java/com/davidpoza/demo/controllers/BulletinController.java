package com.davidpoza.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.davidpoza.demo.dto.ArticleDTO;
import com.davidpoza.demo.models.Bulletin;
import com.davidpoza.demo.services.FeedService;
import com.davidpoza.demo.services.OpenAIService;


@RestController
@RequestMapping("/bulletins")
public class BulletinController {
  @Autowired
  OpenAIService openAI;

  @Autowired
  FeedService feedService;

  @Value("${llm-model}")
  private String model;


  @RequestMapping("/generate")
  public ArrayList<ArticleDTO> generate() {
    return feedService.extractArticles();
  }
}
