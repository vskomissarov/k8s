package com.vkomissarov.customer.rest;

import com.vkomissarov.customer.dto.OrderDto;
import com.vkomissarov.customer.service.CustomerOrderService;
import com.vkomissarov.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.Set;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class CustomerOrderController {

    private final CustomerService customerService;
    private final CustomerOrderService customerOrderService;

    /**
     *  Create a new order for the given "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param order      the order to create.
     */
    @PostMapping("/customer-orders/{customerId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable String customerId, @Valid @RequestBody OrderDto order) {
        log.info("REST request to save Order : {} for Customer ID: {}", order, customerId);
        return customerOrderService.createOrder(customerId, order);
    }

    /**
     *  Updates an existing order for the given "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param order      the order to update.
     */
    @PutMapping("/customer-orders/{customerId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String customerId, @Valid @RequestBody OrderDto order) {
        return customerOrderService.updateOrder(customerId, order);
    }

    /**
     * Get all the orders.
     *
     * @param customerId the id of the customer.
     */
    @GetMapping("/customer-orders/{customerId}")
    public Set<OrderDto> getAllOrders(@PathVariable String customerId) {
        log.info("REST request to get all Orders for Customer: {}", customerId);
        return customerOrderService.findAllOrders(customerId);
    }

    /**
     * Get the "orderId" order for the "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param orderId    the id of the order to retrieve.

     */
    @GetMapping("/customer-orders/{customerId}/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String customerId, @PathVariable String orderId) {
        log.info("REST request to get Order : {} for Customer: {}", orderId, customerId);
        return customerOrderService.findOrderByIdAndCustomerId(customerId, orderId);
    }

    /**
     * Delete the "orderId" order for the "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param orderId    the id of the order to delete.

     */
    @DeleteMapping("/customer-orders/{customerId}/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String customerId, @PathVariable String orderId) {
        log.info("REST request to delete Order : {} for Customer: {}", orderId, customerId);
        return customerOrderService.deleteOrderByIdAndCustomerId(customerId, orderId);
    }
}
