package br.com.adriane.kafka.atmostonce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "at.most.once.topic")
    public void listen(String message) throws InterruptedException {
        Thread.sleep(20);
        log.info("Message consumed={}",message);
    }
}
