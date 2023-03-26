package com.schambeck.design.outbox.order.usecase;

import com.schambeck.design.outbox.exception.BusinessException;
import com.schambeck.design.outbox.exception.NotFoundException;
import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.schambeck.design.outbox.order.StatusOrder.CLOSED;

@Service
@RequiredArgsConstructor
public class CloseOrder {
    private final OrderRepository repository;

    public OrderEntity execute(UUID id) {
        OrderEntity order = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Entity %s not found".formatted(id)));
        validateStatus(order);
        validateItems(order);
        
        repository.updateStatus(id, CLOSED);
        return order;
    }
    
    private static void validateStatus(OrderEntity order) {
        if (order.getStatus() == CLOSED) {
            throw new BusinessException("Order already closed: %s".formatted(order.getId()));
        }
    }

    private void validateItems(OrderEntity order) {
        if (order.getItems().isEmpty()) {
            throw new BusinessException("Order has no items: %s".formatted(order.getId()));
        }
    }
}
