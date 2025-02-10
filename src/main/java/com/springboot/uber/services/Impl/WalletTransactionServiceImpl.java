package com.springboot.uber.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.uber.entities.WalletTransaction;
import com.springboot.uber.repositories.WalletTransactionRepository;
import com.springboot.uber.services.WalletTransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction WalletTransaction) {
        WalletTransaction walletTransaction = modelMapper.map(WalletTransaction, WalletTransaction.class);
        walletTransactionRepository.save(walletTransaction);
    }

}
