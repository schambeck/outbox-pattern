package com.schambeck.design.outbox.outbox;

import com.schambeck.design.outbox.outbox.entity.OutboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface OutboxRepository extends JpaRepository<OutboxEntity, UUID> {
    List<OutboxEntity> findAllByCreatedDateBefore(LocalDateTime createdDate);
}
