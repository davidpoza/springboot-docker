package com.davidpoza.demo.services;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class OpenAIServiceImpl implements OpenAIService {

  private final WebClient webClient;

  private static final Logger LOGGER = Logger.getLogger(OpenAIServiceImpl.class.getName());


  public OpenAIServiceImpl(@Value("${litellm-url}") String baseURL) {
    this.webClient = WebClient.builder().baseUrl(baseURL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
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

}
