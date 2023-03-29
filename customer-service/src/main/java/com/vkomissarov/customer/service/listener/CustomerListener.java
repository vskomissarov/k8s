package com.vkomissarov.customer.service.listener;

import com.vkomissarov.customer.data.Address;
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
        if (!Objects.isNull(entity.getBillingAddress())) {
            Address address = new Address();
            address.setId(new ObjectId().toString());
            entity.setBillingAddress(address);
        }
        return entity;
    }
}
