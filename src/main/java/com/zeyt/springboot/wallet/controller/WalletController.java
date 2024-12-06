package com.zeyt.springboot.wallet.controller;

import com.zeyt.springboot.wallet.dto.WalletRequestDTO;
import com.zeyt.springboot.wallet.dto.WalletResponseDTO;
import com.zeyt.springboot.wallet.servise.WalletService;
import com.zeyt.springboot.wallet.utils.InsufficientFundsException;
import com.zeyt.springboot.wallet.utils.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping("/wallets/{id}")
    public ResponseEntity<WalletResponseDTO> getBalance(@PathVariable("id") UUID id) {
        Long balance = walletService.getBalance(id);
        return ResponseEntity.ok(new WalletResponseDTO(balance));
    }


    @PostMapping("/wallet")
    public ResponseEntity<HttpStatus> update(@RequestBody WalletRequestDTO walletRequestDTO) {
        walletService.updateBalance
                (walletRequestDTO.getWalletId(), walletRequestDTO.getOperationType(), walletRequestDTO.getAmount());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getall")
    public String getAll(){
        return walletService.getAll().toString();
    }


    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFunds(InsufficientFundsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Funds");
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<String> handleWalletNotFoundException(WalletNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON format");
    }

}
