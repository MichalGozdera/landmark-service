package eu.cokeman.cycleareastats.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQAdministrativeAreaPublisher implements AdministrativeAreaPublisher {

  @Autowired private RabbitTemplate rabbitTemplate;

  @Value("${app.exchange.landmark-exchange}")
  private String exchangeName;

  @Autowired private ObjectMapper objectMapper;

  public void send(String message) {}

  @Override
  public void publish(AdministrativeAreaEvent event) {
    System.out.println("out" + event.getArea().getName());
    var messageDto = AdministrativeAreaExternalMapper.INSTANCE.toMessaging(event);
    try {
      String json = objectMapper.writeValueAsString(messageDto);
      rabbitTemplate.convertAndSend(
          exchangeName,
          "landmark",
          json,
          message -> {
            message.getMessageProperties().setHeader("eventType", event.getOperationType());
            message
                .getMessageProperties()
                .setHeader("eventClassFull", messageDto.getClass().getName());
            return message;
          });
    } catch (Exception e) {
      org.slf4j.LoggerFactory.getLogger(RabbitMQAdministrativeAreaPublisher.class)
          .error("Error serializing DTO to JSON or sending message to RabbitMQ", e);
      throw new RuntimeException("Error serializing DTO to JSON or sending message to RabbitMQ", e);
    }
  }
}
