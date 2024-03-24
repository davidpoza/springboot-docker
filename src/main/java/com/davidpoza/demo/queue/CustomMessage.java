package com.davidpoza.demo.queue;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessage(
  @JsonProperty("articleId") Long articleId,
  // @JsonProperty("startedAt") Date startedAt,
  // @JsonProperty("finishedAt") Date finishedAt,
  @JsonProperty("state") String state
  ) implements Serializable {

  public Long articleId() {
    return articleId;
  }

  public String state() {
    return state;
  }


}