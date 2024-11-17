package eu.cokeman.cycleareastats.out;

import eu.cokeman.cycleareastats.entity.Landmark;
import eu.cokeman.cycleareastats.port.out.publishing.LandmarkPublisher;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQLandmarkPublisher implements LandmarkPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app.exchange.landmark-exchange}")
    private String exchangeName;

    public void send(String message) {
    }

    @Override
    public void publish(LandmarkId id) {
        System.out.println("out" + id.value());
        rabbitTemplate.convertAndSend(exchangeName, "landmark", id.value().toString());

    }
}
