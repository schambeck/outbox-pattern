package com.schambeck.design.outbox.order;

import com.schambeck.design.outbox.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Modifying
    @Query("""
                UPDATE OrderEntity o
                   SET o.status = :status
                 WHERE o.id = :id
            """)
    void updateStatus(@Param(value = "id") UUID id,
                      @Param(value = "status") StatusOrder status);
}
