package br.com.adriane.kafka.atmostonce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message){
        log.info("Message sent: {}", message);
        kafkaTemplate.send("at.most.once.topic", message);
    }

}
