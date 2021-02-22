package com.novko.api.mapper;

import com.novko.api.response.CategoryWithSubcategoriesResponse;
import com.novko.internal.categories.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryWithSubcategoriesMapper {

    CategoryWithSubcategoriesMapper INSTANCE = Mappers.getMapper(CategoryWithSubcategoriesMapper.class);

    CategoryWithSubcategoriesResponse toDto(Category category);
    Category toEntity(CategoryWithSubcategoriesResponse categoryResponse);

    List<CategoryWithSubcategoriesResponse> listToDto(List<Category> categories);
}
