package br.com.adriane.kafka.atmostonce;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ProducerFactory;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public Producer<String, String> producer(ProducerFactory<String, String> producerFactory) {
        return producerFactory.createProducer();
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("at.most.once.topic")
                .partitions(3)
                .replicas(1)
                .build();
    }

}
