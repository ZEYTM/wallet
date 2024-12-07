package com.zeyt.springboot.wallet.dto;

import com.zeyt.springboot.wallet.entity.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequestDTO {

    private UUID walletId;
    private OperationType operationType;
    private Long amount;

}

