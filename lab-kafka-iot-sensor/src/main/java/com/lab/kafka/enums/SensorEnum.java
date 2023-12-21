package com.lab.kafka.enums;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

public enum SensorEnum {

    TEMPERATURE {
        @Override
        public BigDecimal getRandomValues() {
            Random rand = new SecureRandom();
            return BigDecimal.valueOf(rand.nextInt(274));
        }
    },
    HUMIDITY {
        @Override
        public BigDecimal getRandomValues() {
            Random rand = new SecureRandom();
            return BigDecimal.valueOf(rand.nextInt(501));
        }
    };

    public abstract BigDecimal getRandomValues();
}
