package com.zeyt.springboot.wallet.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private Long balance;

    public Wallet() {
    }

    public Wallet(Long balance) {
        this.balance = balance;
    }

    public Wallet(OperationType operationType, Long balance) {
        this.balance = balance;
    }

    public Wallet(UUID id, OperationType operationType, Long balance) {
        this.id = id;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
               "uuid=" + id +
               ", amount=" + balance +
               '}';
    }
}
