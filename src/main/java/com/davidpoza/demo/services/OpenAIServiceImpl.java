package com.davidpoza.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Service
public class OpenAIServiceImpl implements OpenAIService {

  private WebClient webClient;

  @Value("${litellm-token}")
  private String token;

  @Value("${litellm-url}")
  private String baseURL;

  @Value("${llm-model}")
  private String model;

  private static final Logger LOGGER = Logger.getLogger(OpenAIServiceImpl.class.getName());

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

  @Override
  public Mono<LLMResponse> getResponse(LLMRequest request) {
    return webClient.post().uri("/v1/chat/completions").body(Mono.just(request), LLMRequest.class).retrieve().bodyToMono(LLMResponse.class);
  }

  private ExchangeFilterFunction logRequest() {
      return (clientRequest, next) -> {
          LOGGER.info("Request: {} {}" + clientRequest.method() + clientRequest.url());
          clientRequest.headers()
                  .forEach((name, values) -> values.forEach(value -> LOGGER.info("{}={}" + name + value)));
          return next.exchange(clientRequest);
      };
  }

  public String summaryArticle(String articleContent) {
    final LLMRequest req = new LLMRequest(this.model);
    req.setMessages(
      new ArrayList<Message>(Arrays.asList(
        new Message("system", "Eres un redactor de la sección de tecnología de un períodico."),
        new Message("user", "Quiero que resumas en una frase muy corta el siguiente artículo: " + articleContent)
      ))
    );
    LLMResponse res = this.getResponse(req).block();
    Choices[] choices = res.getChoices();
    return choices[choices.length -1].getMessage().getContent();
  }
}
