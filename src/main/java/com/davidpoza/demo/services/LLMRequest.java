package com.davidpoza.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

public class LLMRequest {
  @Value("${llm-model}")
  private String model;
  private List<Message> messages;

  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public List<Message> getMessages() {
    return messages;
  }
  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }
}
