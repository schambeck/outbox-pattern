package com.schambeck.design.outbox.order;

import com.schambeck.design.outbox.order.dto.OrderLineMsg;
import com.schambeck.design.outbox.order.dto.OrderLineWeb;
import com.schambeck.design.outbox.order.dto.OrderMsg;
import com.schambeck.design.outbox.order.dto.OrderWeb;
import com.schambeck.design.outbox.order.entity.OrderEntity;
import com.schambeck.design.outbox.order.entity.OrderLineEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "items", ignore = true)
    OrderMsg toMsg(OrderEntity entity);

//    List<OrderMsg> toMsg(List<OrderEntity> entities);

    OrderEntity toEntity(OrderMsg msg);
    
    OrderEntity toEntity(OrderWeb web);
    
    OrderWeb toWeb(OrderEntity order);
    
    List<OrderWeb> toWeb(List<OrderEntity> domains);

    List<OrderLineMsg> mapFromEntity(List<OrderLineEntity> items);

    List<OrderLineEntity> mapFromMsg(List<OrderLineMsg> items);

    List<OrderLineEntity> mapFromWeb(List<OrderLineWeb> items);

    @AfterMapping
    default void mapItems(@MappingTarget OrderEntity.OrderEntityBuilder entity, OrderMsg msg) {
        entity.items(mapFromMsg(msg.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget OrderMsg.OrderMsgBuilder msg, OrderEntity entity) {
        msg.items(mapFromEntity(entity.getItems()));
    }

    @AfterMapping
    default void mapItems(@MappingTarget OrderEntity.OrderEntityBuilder entity, OrderWeb web) {
        entity.items(mapFromWeb(web.getItems()));
    }
}
