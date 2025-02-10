package com.springboot.uber.strategies;

import org.springframework.stereotype.Component;

import com.springboot.uber.entities.enums.PaymentMethod;
import com.springboot.uber.strategies.impl.CashPaymentStrategy;
import com.springboot.uber.strategies.impl.WalletPaymentStrategy;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
