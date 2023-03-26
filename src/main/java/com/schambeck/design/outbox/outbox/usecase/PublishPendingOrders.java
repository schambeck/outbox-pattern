package com.schambeck.design.outbox.outbox.usecase;

import com.schambeck.design.outbox.order.OrderService;
import com.schambeck.design.outbox.outbox.OutboxService;
import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.outbox.entity.OutboxEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublishPendingOrders {
    private final OutboxService outboxService;
    private final OrderService orderService;

    @Transactional
    public void execute() {
        List<OutboxEntity> outboxes = outboxService.findAllByCreatedDateBefore(LocalDateTime.now());
        log.debug("Outboxes found: {}", outboxes.size());
        for (OutboxEntity outbox : outboxes) {
            OrderEntity order = orderService.findById(outbox.getOrderId());
            outboxService.publish(order, outbox.getId());
        }
    }
}
