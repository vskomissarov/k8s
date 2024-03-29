package com.vkomissarov.order.service.feign;

import com.vkomissarov.order.dto.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CustomerClient", url = "${spring.application.microservice-customer.url}")
public interface CustomerClient {

    @RequestMapping(method = RequestMethod.POST, value = "/customer-orders/{customerId}")
    ResponseEntity<OrderDto> createOrder(OrderDto dto,  @PathVariable("customerId") String customerId);

    @RequestMapping(method = RequestMethod.PUT, value = "/customer-orders/{customerId}")
    ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto dto, @PathVariable("customerId") String id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/customer-orders/{customerId}/{orderId}")
    void deleteOrder(@PathVariable String customerId, @PathVariable String orderId);
}
