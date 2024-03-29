package com.vkomissarov.order.service.sender;


import com.vkomissarov.order.mappers.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import com.vkomissarov.order.config.AppPropertiesConfig;
import com.vkomissarov.order.data.Order;
import com.vkomissarov.order.exceptions.CustomerOrderException;
import com.vkomissarov.order.service.feign.CustomerClient;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerSender {

    private final CustomerClient orderClient;
    private final AppPropertiesConfig config;
    private final OrderMapper orderMapper;

    public void postOrder(Order order) {
        try {
            var orderResponse = orderClient.createOrder(orderMapper.toDto(order), order.getCustomerId());
            if (orderResponse.getStatusCode().isError()) {
                log.error("For Order ID: {}, error response: {} is received to create Order in Customer Microservice",
                        order.getId(), orderResponse.getStatusCode());
                throw new CustomerOrderException(orderResponse.getBody().getId(), orderResponse.getStatusCodeValue());
            }
            if (orderResponse.hasBody()) {
                log.error("Order From Response: {}", orderResponse.getBody().getId());
            }
        } catch (Exception e) {
            log.error("For Order ID: {}, cannot create Order in Customer Microservice for reason: {}",
                    order.getId(), ExceptionUtils.getRootCauseMessage(e));
            throw new CustomerOrderException(order.getId(), ExceptionUtils.getRootCauseMessage(e));
        }
    }

    public void putOrder(Order order) {
        try {
            log.info("Order Request URL: {}", config.getOrderUrl() + config.getCustomersUrl() + order.getCustomerId());
            orderClient.updateOrder(orderMapper.toDto(order), order.getCustomerId());
        } catch (Exception e) {
            log.error("For Order ID: {}, cannot update Order in Customer Microservice for reason: {}",
                    order.getId(), ExceptionUtils.getRootCauseMessage(e));
            throw new CustomerOrderException(order.getId(), ExceptionUtils.getRootCauseMessage(e));
        }
    }

    public void deleteOrder(String customerId, @PathVariable String orderId) {
        try {
            log.info("Order Request URL: {}", config.getOrderUrl() + config.getCustomersUrl() +
                    "/" + customerId + "/" + orderId);
            orderClient.deleteOrder(customerId, orderId);
        } catch (Exception e) {
            log.error("For Order ID: {}, cannot delete Order in Customer Microservice for reason: {}",
                    orderId, ExceptionUtils.getRootCauseMessage(e));
            throw new CustomerOrderException(orderId, ExceptionUtils.getRootCauseMessage(e));
        }
    }
}

