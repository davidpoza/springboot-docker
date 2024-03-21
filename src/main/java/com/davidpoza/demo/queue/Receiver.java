package com.davidpoza.demo.queue;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
  private static final Logger log = LoggerFactory.getLogger(Receiver.class);

  @RabbitListener(queues = RabbitMqConfig.queueName)
  public void receiveMessage(final CustomMessage customMessage) {
    log.info("Received message and deserialized to 'CustomMessage': {}", customMessage.toString());
  }
}
