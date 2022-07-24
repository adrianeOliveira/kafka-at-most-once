package br.com.adriane.kafka.atmostonce;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class ClientConsumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(topics = "at.most.once.topic")
    public void listen(ConsumerRecord<String, String> consumerRecord,
                       Acknowledgment acknowledgment) throws InterruptedException {
        acknowledgment.acknowledge();
        String message = consumerRecord.value();
        log.info("Message consumed={}, offset={}",message, consumerRecord.offset());
    }
}
