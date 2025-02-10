package com.springboot.uber.strategies.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.uber.entities.Driver;
import com.springboot.uber.entities.Payment;
import com.springboot.uber.entities.Rider;
import com.springboot.uber.entities.enums.PaymentStatus;
import com.springboot.uber.entities.enums.TransactionMethod;
import com.springboot.uber.repositories.PaymentRepository;
import com.springboot.uber.services.PaymentService;
import com.springboot.uber.services.WalletService;
import com.springboot.uber.strategies.PaymentStrategy;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmmount(), null, payment.getRide(),
                TransactionMethod.RIDE);
        double driversCut = payment.getAmmount() * (1 - PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(), driversCut, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }

}
