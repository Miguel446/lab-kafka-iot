package com.lab.kafka.controller;

import com.lab.kafka.enums.SensorEnum;
import com.lab.kafka.service.SensorStarterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api")
public class SensorStarterController {

    private final SensorStarterService service;

    public SensorStarterController(SensorStarterService service) {
        this.service = service;
    }

    @PostMapping("/temperature")
    ResponseEntity<Object> startTemperatureSensor(@RequestParam Integer numRequests) {
        this.service.start(numRequests, SensorEnum.TEMPERATURE);
        return ResponseEntity.accepted().body(""); // add message to check grafana
    }

    @PostMapping("/humidity")
    ResponseEntity<Object> startHumiditySensor(@RequestParam Integer numRequests) {
        this.service.start(numRequests, SensorEnum.HUMIDITY);
        return ResponseEntity.accepted().body(""); // add message to check grafana
    }
}
