package com.schambeck.design.outbox.outbox.usecase;

import com.schambeck.design.outbox.outbox.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteOutbox {
    private final OutboxRepository repository;

    public void execute(UUID id) {
        repository.deleteById(id);
    }
}
