package com.vkomissarov.customer.service;


import com.vkomissarov.customer.data.Customer;
import com.vkomissarov.customer.dto.OrderDto;
import com.vkomissarov.customer.exceptions.EntityNotFoundException;
import com.vkomissarov.customer.mappers.OrderMapper;
import com.vkomissarov.customer.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.vkomissarov.customer.utils.StringUtils.validate;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CustomerOrderService {
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public ResponseEntity<OrderDto> createOrder(String customerId, OrderDto order) {
        validate(customerId);
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        customer.addOrder(orderMapper.toEntity(order));
        customerRepository.save(customer);

        return ResponseEntity.ok()
                .body(order);
    }


    @Transactional
    public ResponseEntity<OrderDto> updateOrder(String customerId, OrderDto order) {
        validate(customerId);
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        final var orderSet = customer.getOrders().stream()
                .map(o -> Objects.equals(o.getId(), order.getId()) ? orderMapper.toEntity(order) : o)
                .collect(Collectors.toSet());

        customer.setOrders(orderSet);
        customerRepository.save(customer);
        return ResponseEntity.ok()
                .body(order);

    }

    public Set<OrderDto> findAllOrders(String customerId) {
        validate(customerId);
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        return customer.getOrders().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toSet());

    }

    public ResponseEntity<OrderDto> findOrderByIdAndCustomerId(String customerId, String orderId) {
        validate(customerId);
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        return customer.getOrders().stream()
                .filter(order -> Objects.equals(order.getId(), orderId))
                .findFirst()
                .map(orderMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @Transactional
    public ResponseEntity<Void> deleteOrderByIdAndCustomerId(String customerId, String orderId) {
        validate(customerId);
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, customerId));

        customer.getOrders().removeIf((order) -> Objects.equals(order.getId(), orderId));

        customerRepository.save(customer);
        return ResponseEntity.noContent().build();
    }
}
