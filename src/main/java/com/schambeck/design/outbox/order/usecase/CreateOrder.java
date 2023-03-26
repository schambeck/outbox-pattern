package com.schambeck.design.outbox.order.usecase;

import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.schambeck.design.outbox.order.StatusOrder.CREATED;

@Service
@RequiredArgsConstructor
public class CreateOrder {
	private final OrderRepository repository;
	public OrderEntity execute(OrderEntity order) {
		order.setStatus(CREATED);
		return repository.save(order);
    }
}
