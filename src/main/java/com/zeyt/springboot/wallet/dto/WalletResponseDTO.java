package com.zeyt.springboot.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WalletResponseDTO {

    private UUID id;
    private Long balance;
}
