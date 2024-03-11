package com.davidpoza.demo.services;

import reactor.core.publisher.Mono;

public interface OpenAIService {
  public Mono<LLMResponse> getResponse(LLMRequest request);
}
