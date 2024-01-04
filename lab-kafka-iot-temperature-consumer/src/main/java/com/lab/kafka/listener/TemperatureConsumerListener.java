package com.lab.kafka.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TemperatureConsumerListener {

	@KafkaListener(groupId = "temperature-group", topics = "temperature-topic", containerFactory = "validMessageContainerFactory")
	public void processTemperature(String temperature) {
		log.info("Process Temperature: {}", temperature);
	}

}
