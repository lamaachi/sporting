package org.projet.terainservice.RabbitMQ;


import org.projet.terainservice.dtos.TerrainEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TerrainEventListener {

    @RabbitListener(queues = "terrain.Queue")
    public void handleTerrainEvent(TerrainEvent event) {
        System.out.println("Received Event: " + event.getEventType() + ", Data: " + event.getData());
    }
}
