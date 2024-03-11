package com.davidpoza.demo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.davidpoza.demo.services.LLMRequest;
import com.davidpoza.demo.services.LLMResponse;
import com.davidpoza.demo.services.Message;
import com.davidpoza.demo.services.OpenAIService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tags")
public class TagController {

  @Autowired
  OpenAIService openAI;

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

  @RequestMapping("/test2")
  public Mono<LLMResponse> test2() {
    final LLMRequest req = new LLMRequest();
    req.setMessages(
      new ArrayList<Message>(Arrays.asList(
        new Message("system", "You are a helpful assistant."),
        new Message("user", "Hola!")
      ))
    );

    return this.openAI.getResponse(req);
  }
}
