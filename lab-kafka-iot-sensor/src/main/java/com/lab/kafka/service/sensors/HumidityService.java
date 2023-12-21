package com.lab.kafka.service.sensors;

import com.lab.kafka.client.HumidityClient;

import com.lab.kafka.enums.SensorEnum;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class HumidityService implements SensorInterface {

    private final HumidityClient client;

    public HumidityService(HumidityClient client) {
        this.client = client;
    }

    @Async
    public void send() {
        BigDecimal humidity = SensorEnum.HUMIDITY.getRandomValue();

        // max possible value is 500
        BigDecimal humidityPercentage = humidity.multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(500),
                RoundingMode.HALF_EVEN);
        try {
            this.client.send(humidityPercentage);
            log.info(humidityPercentage.toString().concat("%"));
        } catch (FeignException e) {
            log.error("Error while sending humidity data", e);
        }
    }

}
