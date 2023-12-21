package com.lab.kafka.enums;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Random;

public enum SensorEnum {

    TEMPERATURE {
        Random rand = new SecureRandom();

        @Override
        public BigDecimal getRandomValue() {
            return BigDecimal.valueOf(rand.nextInt(374));
        }
    },
    HUMIDITY {
        Random rand = new SecureRandom();

        @Override
        public BigDecimal getRandomValue() {
            return BigDecimal.valueOf(rand.nextInt(501));
        }
    };

    public abstract BigDecimal getRandomValue();
}
