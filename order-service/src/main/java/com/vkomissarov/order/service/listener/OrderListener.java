package com.vkomissarov.order.service.listener;

import com.vkomissarov.order.data.Order;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class OrderListener implements BeforeConvertCallback<Order> {

    @Override
    public Order onBeforeConvert(Order entity, String collection) {
        log.info("entity.getStatus() = " + entity.getStatus());
        entity.getProducts().forEach(product -> {
        });
        if (Objects.isNull(entity.getShippingAddress().getId())) {
            entity.getShippingAddress().setId(new ObjectId().toString());
        }
        return entity;
    }
}
