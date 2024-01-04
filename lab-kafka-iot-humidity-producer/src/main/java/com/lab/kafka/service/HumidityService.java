package com.lab.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class HumidityService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendHumidity(String temperature) {
        log.info("Sending humidity to Kafka");
        kafkaTemplate.send("humidity-topic", temperature).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Humidity: {}", temperature);
                log.info("Partition: {}", result.getRecordMetadata().partition());
            } else {
                log.error("Error while sending humidity", ex);
            }
        });
    }
}
