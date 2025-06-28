package eu.cokeman.cycleareastats.in.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import eu.cokeman.cycleareastats.mapper.area.AdministrativeAreaExternalMapper;
import eu.cokeman.cycleareastats.openapi.model.AdministrativeAreaEventDto;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RabbitMQLandmarkListener {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RabbitMQLandmarkListener.class);
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    @RabbitListener(queues = "${app.queue.landmark-queue}")
    public void receiveMessage(String body) throws IOException {
        System.out.println(body);
        AdministrativeAreaEventDto eventDto = objectMapper.readValue(body, AdministrativeAreaEventDto.class);
        var message = AdministrativeAreaExternalMapper.INSTANCE.fromMessaging(eventDto);
        System.out.println(message);
    }

}
