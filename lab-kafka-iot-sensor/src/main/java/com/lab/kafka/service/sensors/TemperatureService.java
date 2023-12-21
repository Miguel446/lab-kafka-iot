package com.lab.kafka.service.sensors;

import com.lab.kafka.client.TemperatureClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TemperatureService implements SensorInterface {

    private final TemperatureClient client;

    public TemperatureService(TemperatureClient client) {
        this.client = client;
    }

    // async
    public void send(BigDecimal value) {
        // convert from celsius to kelvin
        this.client.send(value);
    }

}
