package com.zeyt.springboot.wallet.servise;

import com.zeyt.springboot.wallet.entity.OperationType;
import com.zeyt.springboot.wallet.entity.Wallet;
import com.zeyt.springboot.wallet.utils.InsufficientFundsException;
import com.zeyt.springboot.wallet.utils.WalletNotFoundException;
import com.zeyt.springboot.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void updateBalance(UUID id, OperationType operationType, Long amount) {

        Wallet wallet = walletRepository.findById(id).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found"));

        if (operationType == OperationType.WITHDRAW && amount > wallet.getBalance()) {
            throw  new InsufficientFundsException("Insufficient funds");
        }

        if (operationType == OperationType.DEPOSIT) {
            wallet.setBalance(wallet.getBalance() + amount);
        }
        if (operationType == OperationType.WITHDRAW) {
            wallet.setBalance(wallet.getBalance() - amount);
        }
    }

    @Transactional
    public Long getBalance(UUID id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }
}
