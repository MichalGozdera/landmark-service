package eu.cokeman.cycleareastats.in.message;

import com.rabbitmq.client.Channel;
import eu.cokeman.cycleareastats.port.in.administrativearea.ImportAdministrativeAreaUseCase;
import eu.cokeman.cycleareastats.valueObject.AdministrativeAreaId;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class RabbitMQLandmarkListener {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RabbitMQLandmarkListener.class);
    private final ImportAdministrativeAreaUseCase importAdministrativeAreaUseCase;

    public RabbitMQLandmarkListener(ImportAdministrativeAreaUseCase importAdministrativeAreaUseCase) {
        this.importAdministrativeAreaUseCase = importAdministrativeAreaUseCase;
    }


    @RabbitListener(queues = "${app.queue.landmark-queue}")
    public void receiveMessage(Message event) {

        var msg = new String(event.getBody());
        AdministrativeAreaId administrativeAreaId = new AdministrativeAreaId(UUID.fromString(msg));
        log.info("Processing landmark with ID {}", administrativeAreaId.value());

        //    importAdministrativeAreaUseCase.processSubunit(administrativeAreaId);
    }

}
