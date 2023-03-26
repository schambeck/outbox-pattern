package com.schambeck.design.outbox.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import static jakarta.persistence.CascadeType.ALL;

@Slf4j
@Getter
@Setter
@ToString
@Entity
@Table(name = "ORDER_LINE")
public class OrderLineEntity implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private UUID id;
    @JsonIgnore
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "ORDER_ID")
    @NotNull(message = "Order is required")
    private OrderEntity order;
    @Column(name = "PRODUCT_ID")
    @NotNull(message = "Product is required")
    private UUID productId;
    @Column(name = "QUANTITY")
    @NotNull(message = "Quantity is required")
    private BigDecimal quantity;
    @Column(name = "PRICE")
    @NotNull(message = "Price is required")
    private BigDecimal price;
    @Column(name = "COST")
    private BigDecimal cost;

    @PrePersist
    public void beforeSave() {
        cost = price.multiply(quantity);
    }
}
