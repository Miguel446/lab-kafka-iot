package com.lab.kafka.service.sensors;

import com.lab.kafka.client.HumidityClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class HumidityService implements SensorInterface {

    private final HumidityClient client;

    public HumidityService(HumidityClient client) {
        this.client = client;
    }

    // async
    public void send(BigDecimal value) {
        // convert to percentage
        this.client.send(value);
    }

}
