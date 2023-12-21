package com.lab.kafka.service.sensors;

import com.lab.kafka.client.TemperatureClient;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class TemperatureService implements SensorInterface {

    private final TemperatureClient client;

    public TemperatureService(TemperatureClient client) {
        this.client = client;
    }

    @Async
    public void send(BigDecimal temperature) {
        // converting from kelvin to celsius
        BigDecimal temperatureInCelsius = temperature.subtract(BigDecimal.valueOf(273));

        try {
            this.client.send(temperatureInCelsius);
        } catch (FeignException e) {
            log.error("Error while sending temperature data", e);
        }
    }

}
