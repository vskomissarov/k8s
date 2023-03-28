package com.vkomissarov.customer.dto;

import com.vkomissarov.customer.data.Address;
import com.vkomissarov.customer.data.OrderStatus;
import com.vkomissarov.customer.data.PaymentType;
import com.vkomissarov.customer.data.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
@Getter
@Setter
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
