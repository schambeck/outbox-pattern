package com.schambeck.design.outbox.order;

import com.schambeck.design.outbox.order.dto.OrderMsg;
import com.schambeck.design.outbox.order.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class OrderConsumer {
    private final OrderService controller;
    @RabbitListener(queues = "${order.queue}")
    public void receiveMessage(OrderMsg msg) {
        OrderEntity order = OrderMapper.INSTANCE.toEntity(msg);
        OrderEntity consumed = controller.consume(order);
        log.debug("Consumed: {}", consumed);
    }
}
