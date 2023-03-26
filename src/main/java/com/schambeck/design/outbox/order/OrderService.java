package com.schambeck.design.outbox.order;

import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.order.usecase.CloseOrder;
import com.schambeck.design.outbox.order.usecase.ConsumeOrder;
import com.schambeck.design.outbox.order.usecase.CreateOrder;
import com.schambeck.design.outbox.order.usecase.FindOrder;
import com.schambeck.design.outbox.outbox.entity.OutboxEntity;
import com.schambeck.design.outbox.outbox.usecase.CreateOutbox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final CreateOrder createOrder;
    private final CloseOrder closeOrder;
    private final CreateOutbox createOutbox;
    private final FindOrder findOrder;
    private final ConsumeOrder consumeOrder;
    
    @Transactional
    public OrderEntity create(OrderEntity order) {
        log.debug("Creating: {}", order);
        return createOrder.execute(order);
    }

    @Transactional
    public OrderEntity close(UUID orderId, UUID outboxId) {
        log.debug("Closing: {}", orderId);
        OrderEntity order = closeOrder.execute(orderId);
        createOutbox.execute(OutboxEntity.builder()
                .id(outboxId)
                .orderId(orderId)
                .build());
        return order;
    }

    public OrderEntity findById(UUID id) {
        log.debug("Finding by id: {}", id);
        return findOrder.findById(id);
    }

    public List<OrderEntity> findAll() {
        log.debug("Finding all");
        return findOrder.findAll();
    }
    
    @Transactional
    public OrderEntity consume(OrderEntity order) {
        log.debug("Consume: {}", order);
        return consumeOrder.execute(order);
    }
}
