package com.lab.kafka.enums;

import java.math.BigDecimal;

public enum SensorEnum {

    // add random value generator

    TEMPERATURE{
        @Override
        public BigDecimal getRandomValues(){
            return BigDecimal.valueOf(1);
        }
    }, HUMIDITY {
        @Override
        public BigDecimal getRandomValues() {
            return BigDecimal.valueOf(2);
        }
    };

    public abstract BigDecimal getRandomValues();
}
