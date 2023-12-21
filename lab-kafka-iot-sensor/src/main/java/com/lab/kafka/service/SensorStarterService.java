package com.lab.kafka.service;

import com.lab.kafka.enums.SensorEnum;
import com.lab.kafka.service.sensors.HumidityService;
import com.lab.kafka.service.sensors.SensorInterface;
import com.lab.kafka.service.sensors.TemperatureService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SensorStarterService {

    private final HumidityService humidityService;
    private final TemperatureService temperatureService;

    public SensorStarterService(HumidityService humidityService, TemperatureService temperatureService) {
        this.humidityService = humidityService;
        this.temperatureService = temperatureService;
    }

    public void start(Integer numRequest, SensorEnum sensor) {
        Map<SensorEnum, SensorInterface> map = Map.of(SensorEnum.TEMPERATURE, temperatureService, SensorEnum.HUMIDITY,
                humidityService);

        for (int i = 0; i < numRequest; i++) {
            map.get(sensor).send();
        }
    }

}
