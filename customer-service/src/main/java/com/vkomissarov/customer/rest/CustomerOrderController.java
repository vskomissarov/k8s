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
     * {@code POST  /orders/:customerId} : Create a new order for the given "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param order      the order to create.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the new order, or with status {@code 400 (Bad Request)} if the order has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customer-orders/{customerId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable String customerId, @Valid @RequestBody OrderDto order) {
        log.debug("REST request to save Order : {} for Customer ID: {}", order, customerId);
        return customerOrderService.createOrder(customerId, order);
    }

    /**
     * {@code PUT  /orders/:customerId} : Updates an existing order for the given "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param order      the order to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated order,
     * or with status {@code 400 (Bad Request)} if the order is not valid,
     * or with status {@code 500 (Internal Server Error)} if the order couldn't be updated.
     */
    @PutMapping("/customer-orders/{customerId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String customerId, @Valid @RequestBody OrderDto order) {
        return customerOrderService.updateOrder(customerId, order);
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @param customerId the id of the customer.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/customer-orders/{customerId}")
    public Set<OrderDto> getAllOrders(@PathVariable String customerId) {
        log.debug("REST request to get all Orders for Customer: {}", customerId);
        return customerOrderService.findAllOrders(customerId);
    }

    /**
     * {@code GET  /orders/:customerId/:orderId} : get the "orderId" order for the "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param orderId    the id of the order to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the order, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customer-orders/{customerId}/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable String customerId, @PathVariable String orderId) {
        log.debug("REST request to get Order : {} for Customer: {}", orderId, customerId);
        return customerOrderService.findOrderByIdAndCustomerId(customerId, orderId);
    }

    /**
     * {@code DELETE  /orders/:customerId/:orderId} : delete the "orderId" order for the "customerId" customer.
     *
     * @param customerId the id of the customer.
     * @param orderId    the id of the order to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customer-orders/{customerId}/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String customerId, @PathVariable String orderId) {
        log.debug("REST request to delete Order : {} for Customer: {}", orderId, customerId);
        return customerOrderService.deleteOrderByIdAndCustomerId(customerId, orderId);
    }
}
