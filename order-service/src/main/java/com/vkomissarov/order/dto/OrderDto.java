package com.vkomissarov.order.dto;

import com.vkomissarov.order.data.Address;
import com.vkomissarov.order.data.PaymentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import com.vkomissarov.order.data.OrderStatus;
import com.vkomissarov.order.data.Product;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private String id;

    @NotBlank
    private String customerId;

    private OrderStatus status;

    private Boolean paymentStatus;

    @NotNull
    private PaymentType paymentMethod;

    @NotNull
    private String paymentDetails;

    private AddressDto shippingAddress;

    @NotEmpty
    private Set<ProductDto> products;
}
