package com.vkomissarov.customer.rest;

import com.vkomissarov.customer.dto.CustomerDto;
import com.vkomissarov.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Create a new customer.
     *
     * @param customer the customer to create.
     */
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customer) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customer);

        return customerService.save(customer);
    }

    /**
     *  Updates an existing customer.
     *
     * @param customer the customer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customer,
     */
    @PutMapping("/customers")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customer) throws URISyntaxException {
        log.debug("REST request to update Customer : {}", customer);
        return customerService.save(customer);
    }

    /**
     *  Get all the customers.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @GetMapping("/customers")
    public List<CustomerDto> getAllCustomers() {
        log.debug("REST request to get all Customers");

        return customerService.findAll();
    }

    /**
     * Get the "id" customer.
     *
     * @param id the id of the customer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable String id) {
        log.debug("REST request to get Customer : {}", id);

        return customerService.findById(id);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param id the id of the customer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        log.debug("REST request to delete Customer : {}", id);

        return customerService.deleteById(id);
    }
}
