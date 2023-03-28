package com.vkomissarov.customer.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductDto {

    @NotNull
    private String id;
    @NotNull
    private String name;

    private String description;

    private String modelNumber;
    @NotNull
    private String manufacturerName;
    @NotNull
    @Min(value = 0, message = "Price cannot be less than zero")
    private Double price;

    private String detailInfo;

    private String imageUrl;
    @NotNull
    @Min(value = 0, message = "Quantity cannot be less than zero")
    private Integer quantity;
}
