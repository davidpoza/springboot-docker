package com.davidpoza.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.davidpoza.demo.models.Bulletin;
import com.davidpoza.demo.services.LLMRequest;
import com.davidpoza.demo.services.LLMResponse;
import com.davidpoza.demo.services.Message;
import com.davidpoza.demo.services.OpenAIService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bulletins")
public class BulletinController {
  @Autowired
  OpenAIService openAI;

  @Value("${llm-model}")
  private String model;

  @RequestMapping("/test")
  public List<Item> test() {
    RssReader rssReader = new RssReader();
    List<Item> items;
    try {
      items = rssReader.read("https://www.genbeta.com/feedburner.xml").toList();
      System.out.println(items);
      return items;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  @RequestMapping("/generate")
  public ArrayList<String> generate() {
    RssReader rssReader = new RssReader();
    List<Item> items;
    try {
      items = rssReader.read("https://www.genbeta.com/feedburner.xml").toList();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }

    Bulletin bulletin = new Bulletin();
    for (Item item : items.subList(0, 3)) {
      String summary = item.getDescription().orElse("");
      bulletin.addHeadline(this.openAI.summaryArticle(summary));
    }

    return bulletin.getHeadlines();
  }
}
