package com.vkomissarov.customer.mappers;

import com.vkomissarov.customer.data.Address;
import com.vkomissarov.customer.data.Customer;
import com.vkomissarov.customer.dto.AddressDto;
import com.vkomissarov.customer.dto.CustomerDto;
import com.vkomissarov.customer.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = { OrderMapper.class, AddressMapper.class })
public interface CustomerMapper {

    @Mapping(target="id", source="id")
    @Mapping(target="firstName", source="firstName")
    @Mapping(target="middleName", source="middleName")
    @Mapping(target="lastName", source="lastName")
    @Mapping(target="paymentDetails", source="paymentDetails")
    @Mapping(target="createdAt", source="createdAt")
    @Mapping(target="updatedAt", source="updatedAt")
    @Mapping(target="version", source="version")
    Customer toEntity(CustomerDto dto);


    @Mapping(target="id", source="id")
    @Mapping(target="firstName", source="firstName")
    @Mapping(target="middleName", source="middleName")
    @Mapping(target="lastName", source="lastName")
    @Mapping(target="paymentDetails", source="paymentDetails")
    @Mapping(target="createdAt", source="createdAt")
    @Mapping(target="updatedAt", source="updatedAt")
    @Mapping(target="version", source="version")
    CustomerDto toDto(Customer entity);



    void updateCustomerFromDto(CustomerDto customerDto, @MappingTarget Customer customer);

    default CustomerDto convertToDto(Customer entity) {
        Set<OrderDto> orders = new HashSet<>();
        AddressDto addressBuild = null;

        if (!CollectionUtils.isEmpty(entity.getOrders())) {
            orders = entity.getOrders().stream()
                    .map(order -> OrderDto.builder()
                            .id(order.getId())
                            .customerId(order.getCustomerId())
                            .status(order.getStatus())
                            .paymentStatus(order.getPaymentStatus())
                            .paymentMethod(order.getPaymentMethod())
                            .paymentDetails(order.getPaymentDetails())
                            .shippingAddress(Optional.ofNullable(order.getShippingAddress())
                                    .map(addr -> AddressDto.builder()
                                            .streetName(addr.getStreetName())
                                            .streetNumber(addr.getStreetNumber())
                                            .additionalInfo(addr.getAdditionalInfo())
                                            .zipCode(addr.getZipCode())
                                            .city(addr.getCity())
                                            .state(addr.getState())
                                            .country(addr.getCountry())
                                            .build())
                                    .orElseGet(() -> AddressDto.builder().build())
                            )
                            .build())
                    .collect(Collectors.toSet());
        }
        if (Objects.nonNull(entity.getBillingAddress())) {
            Address address = entity.getBillingAddress();
            addressBuild = AddressDto.builder()
                    .streetName(address.getStreetName())
                    .streetNumber(address.getStreetNumber())
                    .additionalInfo(address.getAdditionalInfo())
                    .zipCode(address.getZipCode())
                    .city(address.getCity())
                    .state(address.getState())
                    .country(address.getCountry())
                    .build();

        }

        return CustomerDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .middleName(entity.getMiddleName())
                .lastName(entity.getLastName())
                .paymentDetails(entity.getPaymentDetails())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .version(entity.getVersion())
                .orders(orders)
                .billingAddress(addressBuild)
                .build();
    }
}
