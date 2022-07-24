package br.com.adriane.kafka.atmostonce;

import br.com.adriane.kafka.atmostonce.producer.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final Producer producer;

    public Controller(Producer producer) {
        this.producer = producer;
    }

    @GetMapping
    public void sendMessage() {
        producer.send("Test");
    }
}
