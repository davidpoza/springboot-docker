package com.davidpoza.demo.queue;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomMessage(
  @JsonProperty("articleId") String articleId,
  @JsonProperty("startedAt") Date startedAt,
  @JsonProperty("finishedAt") Date finishedAt,
  @JsonProperty("state") String state
  ) implements Serializable {
}