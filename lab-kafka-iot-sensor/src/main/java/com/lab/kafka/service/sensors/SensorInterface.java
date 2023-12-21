package com.lab.kafka.service.sensors;

import java.math.BigDecimal;

public interface SensorInterface {

    void send(BigDecimal value);
}
