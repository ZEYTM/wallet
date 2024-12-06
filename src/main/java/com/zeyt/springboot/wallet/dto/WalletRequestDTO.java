package com.zeyt.springboot.wallet.dto;

import com.zeyt.springboot.wallet.entity.OperationType;

import java.util.UUID;

public class WalletRequestDTO {
    private UUID walletId;
    private OperationType operationType;
    private Long amount;

    public WalletRequestDTO() {
    }

    public WalletRequestDTO(UUID walletId, OperationType operationType, Long amount) {
        this.walletId = walletId;
        this.operationType = operationType;
        this.amount = amount;
    }

    public UUID getWalletId() {
        return walletId;
    }

    public void setWalletId(UUID walletId) {
        this.walletId = walletId;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}

