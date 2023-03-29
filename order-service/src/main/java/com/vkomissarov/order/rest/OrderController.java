package com.vkomissarov.order.rest;

import com.vkomissarov.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vkomissarov.order.config.AppPropertiesConfig;
import com.vkomissarov.order.dto.OrderDto;
import com.vkomissarov.order.utils.StringConstants;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final AppPropertiesConfig config;
    private final OrderService orderService;


    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto order) throws URISyntaxException {
        log.info("REST request to save Order : {}", order);

        var result = orderService.createOrder(order);

        HttpHeaders headers = new HttpHeaders();
        String message = String.format("A new %s is created with identifier %s", StringConstants.ENTITY_ORDER_NAME, result.getId());
        headers.add("X-" + config.getAppName() + "-alert", message);
        headers.add("X-" + config.getAppName() + "-params", result.getId());
        return ResponseEntity.created(new URI("/api/orders/" + result.getId())).headers(headers).body(result);
    }


    @PutMapping("/orders")
    public ResponseEntity<OrderDto> updateOrder(@javax.validation.Valid @RequestBody OrderDto order) throws URISyntaxException {
        log.info("REST request to update Order : {}", order);
        return orderService.updateOrder(order);
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    @GetMapping("/orders")
    public Page<OrderDto> findAll(Pageable pageable) {
        log.info("REST request to get all Orders");
        return orderService.findAll(pageable);
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the order to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the order, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable String id) {
        log.info("REST request to get Order : {}", id);
        return orderService.getById(id);
    }

    /**
     * {@code DELETE  /orders/:id} : delete the "id" order.
     *
     * @param id the id of the order to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("REST request to delete Order : {}", id);
        return orderService.delete(id);
    }
}

