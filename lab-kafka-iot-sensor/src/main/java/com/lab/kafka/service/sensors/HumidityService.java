package com.lab.kafka.service.sensors;

import com.lab.kafka.client.HumidityClient;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class HumidityService implements SensorInterface {

    private final HumidityClient client;

    public HumidityService(HumidityClient client) {
        this.client = client;
    }

    @Async
    public void send(BigDecimal value) {
        // max possible value is 500
        BigDecimal humidityPercentage = value.multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(500));

        try {
            this.client.send(humidityPercentage);
        } catch (FeignException e) {
            log.error("Error while sending humidity data", e);
        }
    }

}
