package com.zeyt.springboot.wallet.dto;

public class WalletResponseDTO {


    private Long balance;

    public WalletResponseDTO(Long balance) {
        this.balance = balance;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
