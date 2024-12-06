package com.zeyt.springboot.wallet.servise;

import com.zeyt.springboot.wallet.entity.OperationType;
import com.zeyt.springboot.wallet.entity.Wallet;
import com.zeyt.springboot.wallet.utils.InsufficientFundsException;
import com.zeyt.springboot.wallet.utils.WalletNotFoundException;
import com.zeyt.springboot.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final ExecutorService executorService = Executors.newCachedThreadPool(); // Пул потоков

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void updateBalance(UUID id, OperationType operationType, Long amount) {
        if (amount == null || amount <= 0) {
            throw new InsufficientFundsException("Amount must be greater than 0");
        }

        Wallet wallet = walletRepository.findById(id).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found"));

        if (operationType == OperationType.WITHDRAW && amount > wallet.getBalance()) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        // Асинхронная обработка в пуле потоков
        executorService.submit(() -> {
            if (operationType == OperationType.DEPOSIT) {
                wallet.setBalance(wallet.getBalance() + amount);
            } else if (operationType == OperationType.WITHDRAW) {
                wallet.setBalance(wallet.getBalance() - amount);
            }
            walletRepository.save(wallet); // Сохранение изменений в базу данных
        });
    }

    @Transactional
    public Long getBalance(UUID id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() ->
                new WalletNotFoundException("Wallet not found"));
        return wallet.getBalance();
    }

    @Transactional
    public List<Wallet> getAll() {
        return walletRepository.findAll();
    }
}
