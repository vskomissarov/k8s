package com.vkomissarov.customer.service.listener;

import com.vkomissarov.customer.data.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertCallback;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerListener implements BeforeConvertCallback<Customer> {

    @Override
    public Customer onBeforeConvert(Customer entity, String collection) {
        System.out.println("Customer Name = " + entity.getLastName());
        if (Objects.isNull(entity.getBillingAddress().getId())) {
            entity.getBillingAddress().setId(new ObjectId().toString());
        }
        return entity;
    }
}
