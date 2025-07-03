package eu.cokeman.cycleareastats.in.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import eu.cokeman.cycleareastats.valueObject.EntityEventType;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQLandmarkListener {

  private static final org.slf4j.Logger log =
      LoggerFactory.getLogger(RabbitMQLandmarkListener.class);
  private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

  @RabbitListener(queues = "${app.queue.landmark-queue}")
  public void receiveMessage(String body, Message message) throws IOException {
    String eventType = null;
    String eventClass = null;
    MessageProperties props = message.getMessageProperties();
    if (props != null && props.getHeaders() != null) {
      Object typeHeader = props.getHeaders().get("eventType");
      if (typeHeader != null) {
        eventType = typeHeader.toString();
      }
      Object classHeader = props.getHeaders().get("eventClassFull");
      if (classHeader != null) {
        eventClass = classHeader.toString();
      }
    }
    if (eventType == null) {
      log.warn("Missing eventType header, cannot determine event type");
      return;
    }
    if (eventClass == null) {
      log.warn("Missing eventClass header, cannot determine event class");
      return;
    }
    EntityEventType entityEventType;
    try {
      entityEventType = EntityEventType.valueOf(eventType);
    } catch (IllegalArgumentException ex) {
      log.warn("Unknown event type: {}", eventType);
      return;
    }
    log.info("Received {} event of class {}", entityEventType, eventClass);
    try {
      Class<?> clazz = Class.forName(eventClass);
      Object eventDto = objectMapper.readValue(body, clazz);
      System.out.println(eventDto);
    } catch (ClassNotFoundException e) {
      log.error("Event class not found: {}", eventClass, e);
    }
  }
}
