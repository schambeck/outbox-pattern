package com.schambeck.design.outbox.outbox;

import com.schambeck.design.outbox.order.OrderMapper;
import com.schambeck.design.outbox.order.OrderProducer;
import com.schambeck.design.outbox.order.dto.OrderMsg;
import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.outbox.entity.OutboxEntity;
import com.schambeck.design.outbox.outbox.usecase.DeleteOutbox;
import com.schambeck.design.outbox.outbox.usecase.FindOutbox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OutboxService {
    private final OrderProducer notifier;
    private final DeleteOutbox deleteOutbox;
    private final FindOutbox findOutbox;

    public void publish(OrderEntity order, UUID outboxId) {
        log.debug("Publish: {}", order);
        OrderMsg msg = OrderMapper.INSTANCE.toMsg(order);
        try {
            notifier.sendMessage(msg);
            deleteOutbox.execute(outboxId);
        } catch (Exception e) {
            log.error("Fail to send message {}: {}", order.getId(), e.getMessage());
        }
    }

    public List<OutboxEntity> findAllByCreatedDateBefore(LocalDateTime createdDate) {
        return findOutbox.findAllByCreatedDateBefore(createdDate);
    }
}
