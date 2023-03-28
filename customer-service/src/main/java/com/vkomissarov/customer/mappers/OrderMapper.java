package com.vkomissarov.customer.mappers;

import com.vkomissarov.customer.data.Order;
import com.vkomissarov.customer.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, ProductMapper.class})
public interface OrderMapper {

    @Mapping(target="id", source="id")
    @Mapping(target="customerId", source="customerId")
    @Mapping(target="status", source="status")
    @Mapping(target="paymentStatus", source="paymentStatus")
    @Mapping(target="paymentMethod", source="paymentMethod")
    @Mapping(target="paymentDetails", source="paymentDetails")
    Order toEntity(OrderDto dto);


    @Mapping(target="id", source="id")
    @Mapping(target="customerId", source="customerId")
    @Mapping(target="status", source="status")
    @Mapping(target="paymentStatus", source="paymentStatus")
    @Mapping(target="paymentMethod", source="paymentMethod")
    @Mapping(target="paymentDetails", source="paymentDetails")
    OrderDto toDto(Order entity);
}
