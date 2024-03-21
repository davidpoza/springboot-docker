package com.davidpoza.demo.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  public static final String topicExchangeName = "jobs";
  public static final String queueName = "bulletin-generator";
  public static final String routingKey = "foo.bar";

  // Crea una cola a la que le otorga un nombre y le define su durabilidad.
  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  // Crea un exchange de tipo topic y le asigna un nombre.
	@Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  // Enlaza una cola con un exchange de tipo topic. Con with se define la clave del enlace.
	@Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
  }

  @Bean
  public MessageConverter jsonMessageConverter(){
      return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
      final var rabbitTemplate = new RabbitTemplate(connectionFactory);
      rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
      return rabbitTemplate;
  }

  @Bean
  public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
      return new Jackson2JsonMessageConverter();
  }

}
