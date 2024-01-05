package com.lab.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class HumidityService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // uuid as key for testing purposes only
    public void sendHumidity(String humidity) {
        log.info("Sending humidity to Kafka");
        kafkaTemplate.send("humidity-topic", UUID.randomUUID().toString(), humidity).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Humidity: {}", humidity);
                log.info("Partition: {}", result.getRecordMetadata().partition());
            } else {
                log.error("Error while sending humidity", ex);
            }
        });
    }
}
