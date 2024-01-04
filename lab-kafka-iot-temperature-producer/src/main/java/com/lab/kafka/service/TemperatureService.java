package com.lab.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class TemperatureService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendTemperature(String temperature) {
        log.info("Sending temperature to Kafka");
        kafkaTemplate.send("temperature-topic", temperature).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Temperature: {}", temperature);
                log.info("Partition: {}", result.getRecordMetadata().partition());
            } else {
                log.error("Error while sending temperature", ex);
            }
        });
    }
}
