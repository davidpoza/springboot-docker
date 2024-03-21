package com.davidpoza.demo.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import com.davidpoza.demo.dto.ArticleDTO;
import com.davidpoza.demo.enums.State;
import com.davidpoza.demo.models.Article;
import com.davidpoza.demo.models.ArticlesRepository;
import com.davidpoza.demo.models.Feed;
import com.davidpoza.demo.models.FeedRepository;
import com.davidpoza.demo.queue.CustomMessage;
import com.davidpoza.demo.queue.RabbitMqConfig;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class FeedServiceImpl implements FeedService {

  private WebClient webClient;

  @Value("${litellm-token}")
  private String token;

  @Value("${litellm-url}")
  private String baseURL;

  @Value("${llm-model}")
  private String model;

  private static final Logger LOGGER = Logger.getLogger(OpenAIServiceImpl.class.getName());
  private final RabbitTemplate rabbitTemplate;

  @Autowired
  private ArticlesRepository articlesRepository;

  @Autowired
  private FeedRepository feedRepository;


  public FeedServiceImpl(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }


  @PostConstruct
  public void init() {
    this.webClient = WebClient.builder().baseUrl(this.baseURL != null ? this.baseURL : "")
      .defaultHeaders(headers -> {
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + this.token);
      })
      .filter(logRequest())
      .build();
  }


  private ExchangeFilterFunction logRequest() {
    return (clientRequest, next) -> {
        LOGGER.info("Request: {} {}" + clientRequest.method() + clientRequest.url());
        clientRequest.headers()
                .forEach((name, values) -> values.forEach(value -> LOGGER.info("{}={}" + name + value)));
        return next.exchange(clientRequest);
    };
  }

  public ArrayList<ArticleDTO> extractArticles() {
    RssReader rssReader = new RssReader();
    List<Item> items;
    ArrayList<ArticleDTO> result = new ArrayList<ArticleDTO>();

    List<Feed> feeds = feedRepository.findAll();
    List<String> urls = feeds.stream()
      .map(f -> f.getUrl())
      .collect(Collectors.toList());

    items = rssReader.read(urls).toList();

    for (Item item : items) {
      String title = item.getTitle().orElse("");
      String publishedAt = item.getPubDate().orElse("");
      String content = item.getDescription().orElse("");
      String url = item.getLink().orElse("");

      Article article = new Article();
      article.setTitle(title);
      article.setFullContent(content);
      article.setUrl(url);
      article.setCuredArticle(null);
      articlesRepository.save(article);

      result.add(new ArticleDTO(
          url,
          title,
          publishedAt != null ? new Date(publishedAt) : null,
          content));
      final var message = new CustomMessage(article.getId().toString(), new Date(), null, State.NEW.toString());
      rabbitTemplate.convertAndSend(RabbitMqConfig.topicExchangeName, RabbitMqConfig.routingKey, message);
    }
    return result;
  }

}