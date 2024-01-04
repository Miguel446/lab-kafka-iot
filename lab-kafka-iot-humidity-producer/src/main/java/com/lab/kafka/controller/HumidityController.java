package com.lab.kafka.controller;

import com.lab.kafka.service.HumidityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/api")
public class HumidityController {

    private final HumidityService service;

    public HumidityController(HumidityService service) {
        this.service = service;
    }

    @PostMapping("/humidity")
    ResponseEntity<Object> post(@RequestBody BigDecimal value) {
        service.sendHumidity(value.toString());
        return ResponseEntity.accepted().body("Humidity accepted: " + value);
    }
}
