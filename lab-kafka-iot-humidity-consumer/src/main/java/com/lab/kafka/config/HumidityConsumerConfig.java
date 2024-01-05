package com.lab.kafka.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

@Log4j2
@RequiredArgsConstructor
@Configuration
public class HumidityConsumerConfig {

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> humidityContainerFactory(
			ConsumerFactory<String, String> consumerFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> validMessageContainerFactory(
			ConsumerFactory<String, String> consumerFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory);
		factory.setRecordInterceptor(validMessage());
		return factory;
	}

	private RecordInterceptor<String, String> validMessage() {
		return (rec, consumer) -> {
			if (rec.value().isEmpty()) {
				log.warn("Empty humidity");
			}
			return rec;
		};
	}

}
