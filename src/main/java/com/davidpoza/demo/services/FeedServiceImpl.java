package com.davidpoza.demo.services;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
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

  private static final Logger log = Logger.getLogger(OpenAIServiceImpl.class.getName());
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
        log.info("Request: {} {}" + clientRequest.method() + clientRequest.url());
        clientRequest.headers()
                .forEach((name, values) -> values.forEach(value -> log.info("{}={}" + name + value)));
        return next.exchange(clientRequest);
    };
  }

  public ArrayList<ArticleDTO> extractArticles() {
    RssReader rssReader = new RssReader();
    List<Item> items;
    ArrayList<ArticleDTO> result = new ArrayList<ArticleDTO>();
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    List<Feed> feeds = feedRepository.findAll();
    List<String> urls = feeds.stream()
      .map(f -> f.getUrl())
      .collect(Collectors.toList());

    items = rssReader.read(urls).toList();

    for (Item item : items) {
      String title = item.getTitle().orElse("");
      ZonedDateTime publishedAt = item.getPubDateZonedDateTime().orElse(null);
      String content = item.getDescription().orElse("");
      String url = item.getLink().orElse("");

      Article existingArticle =articlesRepository.findByUrl(url);
      if (existingArticle == null) {
        Article article = new Article();
        article.setTitle(title);
        article.setFullContent(content);
        article.setUrl(url);
        article.setCuredArticle(null);
        articlesRepository.save(article);
        log.info(url + " " + publishedAt);
        try {
          result.add(new ArticleDTO(
              url,
              title,
              Date.from(publishedAt.toInstant()),
              content));
          final CustomMessage message = new CustomMessage(article.getId(), State.NEW.toString());
          rabbitTemplate.convertAndSend(RabbitMqConfig.topicExchangeName, RabbitMqConfig.routingKey, message);
        } catch (Exception e) {
          log.warning("Cannot send to queue: " + url + " " + publishedAt);
        }
      }
    }
    return result;
  }

}
