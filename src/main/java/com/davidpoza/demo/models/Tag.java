package com.davidpoza.demo.models;


import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Tag {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private UUID id;

  public UUID getId() {
    return id;
  }


}
