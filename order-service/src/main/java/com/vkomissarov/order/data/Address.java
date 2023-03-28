package com.vkomissarov.order.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * A Address.
 */
@Data
@NoArgsConstructor
public class Address implements Serializable {


    @Id
    private String id;

    private String streetName;

    private String streetNumber;

    private String additionalInfo;

    private String zipCode;

    private String city;

    private String state;

    private String country;
}

