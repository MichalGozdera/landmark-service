package eu.cokeman.cycleareastats.out;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.cokeman.cycleareastats.events.AdministrativeAreaEvent;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.port.out.publishing.AdministrativeAreaPublisher;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQAdministrativeAreaPublisher implements AdministrativeAreaPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.exchange.landmark-exchange}")
    private String exchangeName;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(String message) {
    }

    @Override
    public void publish(AdministrativeAreaEvent event) {
        System.out.println("out" + event.getArea().getName());
        var messageDto = AdministrativeAreaExternalMapper.INSTANCE.toMessaging(event);
        try {
            String json = objectMapper.writeValueAsString(messageDto);
            rabbitTemplate.convertAndSend(exchangeName, "landmark", json);
        } catch (Exception e) {
            throw new RuntimeException("Błąd serializacji DTO do JSON", e);
        }
    }
}
