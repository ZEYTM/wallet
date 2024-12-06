package com.zeyt.springboot.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class, args);

//        WalletService walletService = context.getBean(WalletService.class);
////        Wallet wallet2 = new Wallet(12000L);
////        walletService.addWallet(wallet2);
//        walletService.updateBalance(UUID.fromString
//                ("aac1439d-d9b4-4287-bc0a-ca6bd5879bce"), OperationType.WITHDRAW, 50000L);
////        System.out.println(walletService
////                .getBalance(UUID.fromString("aac1439d-d9b4-4287-bc0a-ca6bd5879bce")));

    }
}
