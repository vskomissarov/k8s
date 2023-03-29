package com.vkomissarov.order.service;

import com.vkomissarov.order.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import com.vkomissarov.order.data.Order;
import com.vkomissarov.order.dto.OrderDto;
import com.vkomissarov.order.exceptions.BadRequestAlertException;
import com.vkomissarov.order.exceptions.EntityNotFoundException;
import com.vkomissarov.order.repo.OrderRepository;
import com.vkomissarov.order.service.sender.CustomerSender;
import com.vkomissarov.order.utils.StringConstants;

import javax.validation.Valid;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerSender customerSender;

    @Transactional
    public OrderDto createOrder(OrderDto dto) {
        if (dto.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "A new order cannot already have an ID");
        }
        final var order = orderRepository.save(orderMapper.toEntity(dto));
        customerSender.postOrder(order);
        return orderMapper.toDto(order);
    }

    @Transactional
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto dto) {
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id ", StringConstants.ENTITY_ORDER_NAME, " id is null");
        }
        var order = orderRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException(Order.class, dto.getId()));

        orderMapper.updateCustomerFromDto(dto, order);
        orderRepository.save(order);
        customerSender.putOrder(order);
        return ResponseEntity.ok().body(orderMapper.toDto(order));
    }

    /**
     * {@code GET  /orders} : get all the orders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orders in body.
     */
    public Page<OrderDto> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toDto);
    }

    /**
     * {@code GET  /orders/:id} : get the "id" order.
     *
     * @param id the id of the order to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the order, or with status {@code 404 (Not Found)}.
     */
    public ResponseEntity<OrderDto> getById(String id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Order.class, id));
        return ResponseEntity.ok().body(orderMapper.toDto(order));
    }

    /**
     * {@code DELETE  /orders/:id} : delete the "id" order.
     *
     * @param id the id of the order to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @Transactional
    public ResponseEntity<Void> delete(String id) {
        orderRepository.findById(id)
                .ifPresent(order -> {
                    customerSender.deleteOrder(order.getCustomerId(), order.getId());
                    orderRepository.deleteById(id);
                });

        return ResponseEntity.noContent().build();
    }
}
