package com.schambeck.design.outbox.order.entity;

import com.schambeck.design.outbox.order.StatusOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.UUID;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER")
@ToString(exclude = "items")
public class OrderEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = UUID)
    @Column(name = "ID")
    @NotNull(message = "Id is required")
    private UUID id;
    @Column(name = "CLIENT_ID")
    @NotNull(message = "Client is required")
    private UUID clientId;
    @Column(name = "ISSUED_DATE")
    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;
    @Enumerated(STRING)
    @Column(name = "STATUS")
    @NotNull(message = "Status is required")
    private StatusOrder status;
    @Column(name = "TOTAL_COST")
    @NotNull(message = "Total cost is required")
    private BigDecimal totalCost;
    @OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderLineEntity> items = new ArrayList<>();

    public List<OrderLineEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

//    public void addItems(List<OrderLineEntity> items) {
//        items.forEach(this::addItem);
//    }
    
//    public void addItem(OrderLineEntity item) {
//        items.add(item);
//        item.setOrder(this);
//    }

    public static OrderEntityBuilder builder() {
        return new CustomOrderEntityBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class CustomOrderEntityBuilder extends OrderEntityBuilder {
        @Override
        public OrderEntity build() {
            OrderEntity order = super.build();
            order.items.forEach(item -> item.setOrder(order));
            return order;
        }
    }
}
