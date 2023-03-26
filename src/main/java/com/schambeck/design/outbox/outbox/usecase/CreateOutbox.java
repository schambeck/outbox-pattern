package com.schambeck.design.outbox.outbox.usecase;

import com.schambeck.design.outbox.outbox.entity.OutboxEntity;
import com.schambeck.design.outbox.outbox.OutboxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOutbox {
    private final OutboxRepository repository;
    
    public OutboxEntity execute(OutboxEntity outbox) {
        return repository.save(outbox);
    }
}
