package org.projet.terainservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * @author lamaachi
 **/
@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic terrainAssignmentTopic() {
        return TopicBuilder.name("terrain-assignment-topic").build();
    }
}

