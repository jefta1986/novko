package com.novko.api.mapper;

import com.novko.api.response.CategoryResponse;
import com.novko.internal.categories.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse toDto(Category category);
    Category toEntity(CategoryResponse categoryResponse);

    List<CategoryResponse> listToDto(List<Category> categories);
}
