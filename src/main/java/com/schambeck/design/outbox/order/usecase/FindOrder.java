package com.schambeck.design.outbox.order.usecase;

import com.schambeck.design.outbox.exception.NotFoundException;
import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindOrder {
    private final OrderRepository repository;

    public OrderEntity findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity %s not found".formatted(id)));
    }
    public List<OrderEntity> findAll() {
        return repository.findAll();
    }
}
