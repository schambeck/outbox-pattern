package com.schambeck.design.outbox.outbox.usecase;

import com.schambeck.design.outbox.outbox.OutboxRepository;
import com.schambeck.design.outbox.outbox.entity.OutboxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOutbox {
    private final OutboxRepository repository;

    public List<OutboxEntity> findAllByCreatedDateBefore(LocalDateTime createdDate) {
        return repository.findAllByCreatedDateBefore(createdDate);
    }
}
