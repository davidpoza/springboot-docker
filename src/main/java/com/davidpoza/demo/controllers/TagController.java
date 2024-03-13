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

  @Value("${llm-model}")
  private String model;


}
