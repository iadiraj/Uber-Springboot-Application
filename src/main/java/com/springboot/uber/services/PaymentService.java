package com.springboot.uber.services;

import com.springboot.uber.entities.Payment;
import com.springboot.uber.entities.Ride;
import com.springboot.uber.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride Ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
