package com.example.transactions.Repositories;

import com.example.transactions.Entities.IdempotencyKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IdempotencyRepository extends JpaRepository<IdempotencyKey,Long> {
    Optional<IdempotencyKey> findByIdempotencyKey(String key);

}
