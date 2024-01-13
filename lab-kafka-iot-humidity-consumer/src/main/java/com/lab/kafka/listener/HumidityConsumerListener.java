package com.lab.kafka.listener;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Log4j2
@Component
public class HumidityConsumerListener {

	@KafkaListener(groupId = "humidity-group", topics = "humidity-topic", containerFactory = "validMessageContainerFactory")
	public void processTemperature(String humidity) {
		log.info("Process humidity: {}", humidity);

		WebClient client = WebClient.create("http://device:8085");

		Mono<String> response = client.post()
				.uri("/v1/api/humidity").contentType(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN)
				.bodyValue(humidity)
				.retrieve()
				.bodyToMono(String.class);

		response.subscribe(t -> log.info("Humidity processed: {}", t));
	}

}
