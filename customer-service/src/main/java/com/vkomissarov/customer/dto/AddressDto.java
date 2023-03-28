package com.vkomissarov.customer.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AddressDto {
    private static final long serialVersionUID = 2L;

    private String streetName;

    private String streetNumber;

    private String additionalInfo;

    private String zipCode;

    private String city;

    private String state;

    private String country;
}
