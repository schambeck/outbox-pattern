package com.schambeck.design.outbox.order;

import com.schambeck.design.outbox.order.dto.OrderMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProducer {
    private final RabbitTemplate template;
    @Value("${order-exchange}")
    private String orderExchange;
    @Value("${order-routing-key}")
    private String orderRoutingKey;

    public void sendMessage(OrderMsg msg) {
        log.debug("Sending: {}", msg);
        template.convertAndSend(orderExchange, orderRoutingKey, msg);
    }
}
