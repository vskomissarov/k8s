package com.vkomissarov.order.mappers;


import com.vkomissarov.order.data.Address;
import com.vkomissarov.order.dto.AddressDto;
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
