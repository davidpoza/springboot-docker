package com.davidpoza.demo.queue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.davidpoza.demo.enums.State;
import com.davidpoza.demo.models.Article;
import com.davidpoza.demo.models.ArticlesRepository;
import com.davidpoza.demo.models.CuredArticle;
import com.davidpoza.demo.services.OpenAIService;

@Component
public class Receiver {
  @Autowired
  OpenAIService openAIService;

  @Autowired
  ArticlesRepository articlesRepository;

  private static final Logger log = LoggerFactory.getLogger(Receiver.class);

  @RabbitListener(queues = RabbitMqConfig.queueName)
  public void receiveMessage(final CustomMessage customMessage) throws InterruptedException {
    log.info("Received message and deserialized to 'CustomMessage': {}", customMessage.toString());
    if (customMessage.state().equals(State.NEW.toString())) {
      try {
        Article article = articlesRepository.findById(customMessage.articleId()).orElseThrow();
        String summmaryFromSummary = openAIService.summaryArticle(article.getSummaryContent());
        article.setSummaryContent(summmaryFromSummary);
        articlesRepository.save(article);
      } catch (Exception e) {
        log.warn("#" + customMessage.articleId() + "article not found");
      }
    }
  }
}
