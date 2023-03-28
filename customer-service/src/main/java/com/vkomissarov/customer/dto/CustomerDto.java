package com.vkomissarov.customer.dto;

import com.vkomissarov.customer.data.Address;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private String id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String paymentDetails;

    private Instant createdAt;

    private Instant updatedAt;

    public Integer version;

    private Set<OrderDto> orders;

    private AddressDto billingAddress;

}
