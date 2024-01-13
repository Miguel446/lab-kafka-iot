package com.lab.kafka.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/v1/api")
public class DeviceController {

    private static final double TEMPERATURE_THRESHOLD =  40;
    private static final double HUMIDITY_THRESHOLD =  75;

    @PostMapping("/temperatures")
    public ResponseEntity<Object> postTemperatures(@RequestBody String temperature){
        double t = Double.parseDouble(temperature);

        if(t > TEMPERATURE_THRESHOLD){
            log.warn("Temperature High");
        }
        return ResponseEntity.ok("Received Temperature: " + temperature);
    }

    @PostMapping("/humidity")
    public ResponseEntity<Object> postHumidity(@RequestBody String humidity){
        double h = Double.parseDouble(humidity);

        if(h < HUMIDITY_THRESHOLD){
            log.warn("Humidity Low");
        }
        return ResponseEntity.ok("Received Humidity: " + humidity);
    }
}
