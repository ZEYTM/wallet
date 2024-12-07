package com.zeyt.springboot.wallet.servise;

import com.zeyt.springboot.wallet.entity.OperationType;
import com.zeyt.springboot.wallet.entity.Wallet;
import com.zeyt.springboot.wallet.repository.WalletRepository;
import com.zeyt.springboot.wallet.utils.InsufficientFundsException;
import com.zeyt.springboot.wallet.utils.WalletNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final ExecutorService executorService = Executors.newCachedThreadPool(); // Пул потоков

    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    @Transactional
    public void updateBalance(UUID id, OperationType operationType, Long amount) {
        log.info("Попытка обновления баланса для кошелька с ID: {}, операция: {}, сумма: {}", id, operationType, amount);

        if (amount == null || amount <= 0) {
            log.error("Сумма для обновления должна быть положительным числом, передано: {}", amount);
            throw new InsufficientFundsException("Insufficient Funds");
        }

        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> {
            log.error("Кошелек с ID {} не найден", id);
            return new WalletNotFoundException("Кошелек не найден");
        });

        if (operationType == OperationType.WITHDRAW && amount > wallet.getBalance()) {
            log.error("Недостаточно средств для снятия. Баланс: {}, запрашиваемая сумма: {}", wallet.getBalance(), amount);
            throw new InsufficientFundsException("Insufficient Funds");
        }

        executorService.submit(() -> {
            if (operationType == OperationType.DEPOSIT) {
                wallet.setBalance(wallet.getBalance() + amount);
                log.info("Баланс кошелька с ID {} увеличен на {}, новый баланс: {}", id, amount, wallet.getBalance());
            } else if (operationType == OperationType.WITHDRAW) {
                wallet.setBalance(wallet.getBalance() - amount);
                log.info("Баланс кошелька с ID {} уменьшен на {}, новый баланс: {}", id, amount, wallet.getBalance());
            }
            walletRepository.save(wallet);
            log.info("Кошелек с ID {} успешно обновлен в базе данных", id);
        });
    }

    @Override
    @Transactional
    public Long getBalance(UUID id) {
        log.info("Запрос баланса для кошелька с ID: {}", id);
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> {
            log.error("Кошелек с ID {} не найден", id);
            return new WalletNotFoundException("Кошелек не найден");
        });
        log.info("Баланс кошелька с ID {}: {}", id, wallet.getBalance());
        return wallet.getBalance();
    }

    @Override
    @Transactional
    public List<Wallet> getAll() {
        log.info("Запрос списка всех кошельков");
        List<Wallet> wallets = walletRepository.findAll();
        log.info("Получено {} кошельков", wallets.size());
        return wallets;
    }
}
