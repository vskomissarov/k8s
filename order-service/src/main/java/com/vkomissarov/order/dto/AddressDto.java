package com.vkomissarov.order.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
    private static final long serialVersionUID = 2L;


    @NotNull
    private String streetName;

    @NotNull
    private String streetNumber;

    private String additionalInfo;

    @NotNull
    private String zipCode;

    @NotNull
    private String city;

    private String state;

    @NotNull
    private String country;
}
