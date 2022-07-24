package br.com.adriane.kafka.atmostonce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final ClientProducer clientProducer;

    public Controller(ClientProducer clientProducer) {
        this.clientProducer = clientProducer;
    }

    @GetMapping
    public void sendMessage() throws InterruptedException {
        clientProducer.send("Test");
    }
}
