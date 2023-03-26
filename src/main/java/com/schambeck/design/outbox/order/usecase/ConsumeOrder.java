package com.schambeck.design.outbox.order.usecase;

import com.schambeck.design.outbox.order.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ConsumeOrder {
    public OrderEntity execute(OrderEntity order) {
        log.debug("Consuming: {}", order);
        return order;
    }
}
