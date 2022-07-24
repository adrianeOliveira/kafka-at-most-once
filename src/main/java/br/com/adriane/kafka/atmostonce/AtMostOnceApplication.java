package br.com.adriane.kafka.atmostonce;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

@EnableKafka
@SpringBootApplication
public class AtMostOnceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtMostOnceApplication.class, args);
	}

	@KafkaListener(id = "myId", topics = "at.most.once.topic")
	public void listen(String in) {
		System.out.println(in);
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("at.most.once.topic")
				.partitions(3)
				.replicas(1)
				.build();
	}

	@Bean
	public ApplicationRunner runner(KafkaTemplate<String, String> template) {
		return args -> {
			template.send("at.most.once.topic", "test");
		};
	}

}
