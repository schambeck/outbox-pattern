package com.schambeck.design.outbox.order.dto;

import com.schambeck.design.outbox.order.StatusOrder;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderMsg {
    @ToString.Include
    private UUID id;
    @NotNull(message = "Client is required")
    private UUID clientId;
    @NotNull(message = "Issued date is required")
    private LocalDate issuedDate;
    private StatusOrder status;
    private BigDecimal totalCost;
    @Singular
    private List<OrderLineMsg> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMsg orderMsg = (OrderMsg) o;
        return Objects.equals(id, orderMsg.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
