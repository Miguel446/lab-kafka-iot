package com.lab.kafka.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;

@FeignClient(url = "http://localhost:8082", name = "humidity-client")
public interface HumidityClient {

    @PostMapping("/v1/api/humidity")
    void send(BigDecimal value);
}
