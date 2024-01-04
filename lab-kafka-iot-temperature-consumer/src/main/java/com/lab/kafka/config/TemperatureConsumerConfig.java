package com.lab.kafka.config;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

import java.util.HashMap;

@Log4j2
@Configuration
public class TemperatureConsumerConfig {

	@Bean
	ConsumerFactory<String, String> consumerFactory() {
		var configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> temperatureContainerFactory(
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
				log.warn("Empty temperature");
			}
			return rec;
		};
	}

}
