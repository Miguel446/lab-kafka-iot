package com.lab.kafka.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient(url = "${lab-kafka-iot.temperature-producer}", name = "temperature-client")
public interface TemperatureClient {

    @PostMapping("/v1/api/temperatures")
    void send(BigDecimal value);
}
