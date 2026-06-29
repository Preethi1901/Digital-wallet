package com.example.transactions.services;

import com.example.transactions.Entities.IdempotencyKey;
import com.example.transactions.Repositories.IdempotencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdempotencyService {

    private final IdempotencyRepository idempotencyRepository;

    public Optional<String> getExistingResponse(String key) {

        return idempotencyRepository
                .findByIdempotencyKey(key)
                .map(IdempotencyKey::getResponse);
    }

    public void saveResponse(
            String key,
            String response) {

        IdempotencyKey idempotencyKey =
                new IdempotencyKey();

        idempotencyKey.setIdempotencyKey(key);
        idempotencyKey.setResponse(response);

        idempotencyRepository.save(idempotencyKey);
    }
}