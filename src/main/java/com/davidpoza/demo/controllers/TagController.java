package com.davidpoza.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tags")
public class TagController {

  @RequestMapping("/test")
  public String test() {
    return "tag";
  }
}
