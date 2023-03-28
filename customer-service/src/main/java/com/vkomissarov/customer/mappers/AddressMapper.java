package com.vkomissarov.customer.mappers;


import com.vkomissarov.customer.data.Address;
import com.vkomissarov.customer.data.Order;
import com.vkomissarov.customer.dto.AddressDto;
import com.vkomissarov.customer.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(target = "streetName", source = "streetName")
    @Mapping(target = "streetNumber", source = "streetNumber")
    @Mapping(target = "additionalInfo", source = "additionalInfo")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "country", source = "country")
    Address toEntity(AddressDto dto);

    @Mapping(target = "streetName", source = "streetName")
    @Mapping(target = "streetNumber", source = "streetNumber")
    @Mapping(target = "additionalInfo", source = "additionalInfo")
    @Mapping(target = "zipCode", source = "zipCode")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "state", source = "state")
    @Mapping(target = "country", source = "country")
    AddressDto toDto(Address entity);
}
