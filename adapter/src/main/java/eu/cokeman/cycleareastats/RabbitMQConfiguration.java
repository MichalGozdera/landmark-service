package eu.cokeman.cycleareastats;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class RabbitMQConfiguration {

  @Value("${app.queue.landmark-queue}")
  private String landmarkQueue;

  @Value("${app.exchange.landmark-exchange}")
  private String exchangeName;

  @Bean
  public Queue landmarkQueue() {
    return new Queue(this.landmarkQueue, true);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
