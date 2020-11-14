package com.novko.api.mapper;

import com.novko.api.response.ProductWithImagesResponse;
import com.novko.internal.products.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductWithImagesMapper {

    ProductWithImagesMapper INSTANCE = Mappers.getMapper(ProductWithImagesMapper.class);

    ProductWithImagesResponse toDto(Product product);
    Product toEntity(ProductWithImagesResponse productWithImagesResponse);

    List<ProductWithImagesResponse> listToDto(List<Product> products);
}
