package com.novko.api.mapper;

import com.novko.api.response.CategoryResponse;
import com.novko.api.response.ImageResponse;
import com.novko.internal.products.Images;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageResponse toDto(Images image);
    Images toEntity(ImageResponse imageResponse);

    List<CategoryResponse> listToDto(List<Images> images);
}
