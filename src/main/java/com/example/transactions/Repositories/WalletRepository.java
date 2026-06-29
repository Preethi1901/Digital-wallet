package com.example.transactions.Repositories;

import com.example.transactions.Entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;



@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long>{
    Optional<Wallet> findByUserId(Long userId);

}



