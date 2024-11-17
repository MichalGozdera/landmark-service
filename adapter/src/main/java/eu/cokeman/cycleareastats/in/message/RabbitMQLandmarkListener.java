package eu.cokeman.cycleareastats.in.message;

import com.rabbitmq.client.Channel;
import eu.cokeman.cycleareastats.port.in.ImportLandmarkUseCase;
import eu.cokeman.cycleareastats.valueObject.LandmarkId;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class RabbitMQLandmarkListener {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(RabbitMQLandmarkListener.class);
    private final ImportLandmarkUseCase importLandmarkUseCase;

    public RabbitMQLandmarkListener(ImportLandmarkUseCase importLandmarkUseCase) {
        this.importLandmarkUseCase = importLandmarkUseCase;
    }


    @RabbitListener(queues = "${app.queue.landmark-queue}")
    public void receiveMessage(Message event, Channel channel) {
        try {
            channel.basicAck(1,true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        var msg = new String(event.getBody());
        LandmarkId landmarkId = new LandmarkId(UUID.fromString(msg));
        log.info("Processing landmark with ID {}", landmarkId.value());
        importLandmarkUseCase.processChildren(landmarkId);
    }

}
