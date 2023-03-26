package com.schambeck.design.outbox.order;

import com.schambeck.design.outbox.order.dto.OrderWeb;
import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.outbox.OutboxService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
	private final OrderService orderService;
	private final OutboxService outboxService;

	@PostMapping
	public OrderWeb create(@RequestBody @Valid OrderWeb web) {
		OrderEntity order = OrderMapper.INSTANCE.toEntity(web);
		OrderEntity created = orderService.create(order);
		return OrderMapper.INSTANCE.toWeb(created);
	}

	@PostMapping("{id}/close")
	public void close(@PathVariable("id") UUID id) {
		UUID outboxId = UUID.randomUUID();
		OrderEntity order = orderService.close(id, outboxId);
		outboxService.publish(order, outboxId);
	}

	@GetMapping("{id}")
	public OrderWeb findById(@PathVariable("id") UUID id) {
		OrderEntity order = orderService.findById(id);
		return OrderMapper.INSTANCE.toWeb(order);
	}

	@GetMapping
	public List<OrderWeb> findAll() {
		List<OrderEntity> orders = orderService.findAll();
		return OrderMapper.INSTANCE.toWeb(orders);
	}
}
