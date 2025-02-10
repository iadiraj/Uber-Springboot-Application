package com.springboot.uber.strategies;

import com.springboot.uber.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;

    void processPayment(Payment payment);
}
