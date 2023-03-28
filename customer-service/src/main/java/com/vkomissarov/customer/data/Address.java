package com.vkomissarov.customer.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A Address.
 */
@Getter
@Setter
@NoArgsConstructor
public class Address implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    private String id;

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
