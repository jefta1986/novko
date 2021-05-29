package com.novko.api.mapper;

import com.novko.api.response.ProductResponse;
import com.novko.internal.products.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toDto(Product product);
    Product toEntity(ProductResponse productResponse);

    List<ProductResponse> listToDto(List<Product> products);

    default Page<ProductResponse> pageToDto(Page<Product> products) {
        return products.map(e -> toDto(e));
    }

}
