package com.lab.kafka.controller;

import com.lab.kafka.service.TemperatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("v1/api")
public class TemperatureController {

    private final TemperatureService service;

    public TemperatureController(TemperatureService service) {
        this.service = service;
    }

    @PostMapping("/temperatures")
    ResponseEntity<Object> post(@RequestBody BigDecimal value) {
        service.sendTemperature(value.toString());
        return ResponseEntity.accepted().body("Temperature accepted: " + value);
    }
}
