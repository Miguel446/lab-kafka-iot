package com.lab.kafka.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class HumidityConsumerListener {

	@KafkaListener(groupId = "humidity-group", topics = "humidity-topic", containerFactory = "validMessageContainerFactory")
	public void processTemperature(String humidity) {
		log.info("Process humidity: {}", humidity);
	}

}
