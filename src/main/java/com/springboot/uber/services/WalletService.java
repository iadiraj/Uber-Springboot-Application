package com.springboot.uber.services;

import com.springboot.uber.entities.Ride;
import com.springboot.uber.entities.User;
import com.springboot.uber.entities.Wallet;
import com.springboot.uber.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride,
            TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride,
            TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet findByUser(User user);

    Wallet createNewWallet(User user);
}
