package com.zeyt.springboot.wallet.repository;


import com.zeyt.springboot.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {

    @Override
    Optional<Wallet> findById(UUID uuid);

}
