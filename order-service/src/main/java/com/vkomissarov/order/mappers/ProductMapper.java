package com.vkomissarov.order.mappers;

import com.vkomissarov.order.data.Product;
import com.vkomissarov.order.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "modelNumber", source = "modelNumber")
    @Mapping(target = "manufacturerName", source = "manufacturerName")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "detailInfo", source = "detailInfo")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "quantity", source = "quantity")
    Product toEntity(ProductDto dto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "modelNumber", source = "modelNumber")
    @Mapping(target = "manufacturerName", source = "manufacturerName")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "detailInfo", source = "detailInfo")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "quantity", source = "quantity")
    ProductDto toDto(Product entity);
}
