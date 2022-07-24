package br.com.adriane.kafka.atmostonce;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClientProducer {

    private static final Logger log = LoggerFactory.getLogger(ClientProducer.class);
    private final Producer<String, String> kafkaProducer;

    public ClientProducer(Producer<String, String> producer) {
        this.kafkaProducer = producer;
    }

    public void send(String message) throws InterruptedException {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("at.most.once.topic", message);
        kafkaProducer.send(producerRecord, (metadata, exception) -> {
            log.info("Callback: partition={}, offset={}, topic={}, timestamp={}", metadata.partition(), metadata.offset(), metadata.topic(), metadata.timestamp());
            if (exception != null) {
                log.error("Callback error={}", exception.getLocalizedMessage());
            }
        });

        log.info("Message sent: {}", message);
        Thread.sleep(20);
        kafkaProducer.close();
    }

}
