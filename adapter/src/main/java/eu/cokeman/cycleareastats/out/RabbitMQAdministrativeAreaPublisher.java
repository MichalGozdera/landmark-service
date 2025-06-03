package eu.cokeman.cycleareastats.out;

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

    public void send(String message) {
    }

    @Override
    public void publish(AdministrativeAreaId id) {
        System.out.println("out" + id.value());
        rabbitTemplate.convertAndSend(exchangeName, "landmark", id.value().toString());

    }
}
