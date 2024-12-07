package com.zeyt.springboot.wallet.servise;

import com.zeyt.springboot.wallet.entity.OperationType;
import com.zeyt.springboot.wallet.entity.Wallet;

import java.util.List;
import java.util.UUID;

public interface WalletService {

    void updateBalance(UUID id, OperationType operationType, Long amount);

    Long getBalance(UUID id);

    List<Wallet> getAll();
}
