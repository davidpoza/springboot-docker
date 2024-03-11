package com.davidpoza.demo.services;


class Usage {
  private int prompt_token;
  private int completion_tokens;
  private int total_tokens;

  public int getPrompt_token() {
    return prompt_token;
  }
  public void setPrompt_token(int prompt_token) {
    this.prompt_token = prompt_token;
  }
  public int getCompletion_tokens() {
    return completion_tokens;
  }
  public void setCompletion_tokens(int completion_tokens) {
    this.completion_tokens = completion_tokens;
  }
  public int getTotal_tokens() {
    return total_tokens;
  }
  public void setTotal_tokens(int total_tokens) {
    this.total_tokens = total_tokens;
  }
}


class Choices {
  private String finish_reason;
  private int index;
  private Message message;

  public String getFinish_reason() {
    return finish_reason;
  }
  public void setFinish_reason(String finish_reason) {
    this.finish_reason = finish_reason;
  }
  public int getIndex() {
    return index;
  }
  public void setIndex(int index) {
    this.index = index;
  }
  public Message getMessage() {
    return message;
  }
  public void setMessage(Message message) {
    this.message = message;
  }


}
public class LLMResponse {
  private String id;
  private String model;
  private String object;
  private Usage usage;
  private long created;
  private Choices[] choices;


  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getModel() {
    return model;
  }
  public void setModel(String model) {
    this.model = model;
  }
  public String getObject() {
    return object;
  }
  public void setObject(String object) {
    this.object = object;
  }
  public Usage getUsage() {
    return usage;
  }
  public void setUsage(Usage usage) {
    this.usage = usage;
  }
  public long getCreated() {
    return created;
  }
  public void setCreated(long created) {
    this.created = created;
  }
  public Choices[] getChoices() {
    return choices;
  }
  public void setChoices(Choices[] choices) {
    this.choices = choices;
  }
}
