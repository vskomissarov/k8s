package com.vkomissarov.customer.repo;
import com.vkomissarov.customer.data.Customer;
import com.vkomissarov.customer.data.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data MongoDB repository for the Customer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {


}
