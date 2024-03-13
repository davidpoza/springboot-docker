package com.davidpoza.demo.services;

import java.util.List;


public class LLMRequest {

  private String model;
  private List<Message> messages;

  public LLMRequest(String model) {
    this.model = model;
  }

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
