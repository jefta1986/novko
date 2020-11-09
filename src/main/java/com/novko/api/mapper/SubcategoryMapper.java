package com.novko.api.mapper;

import com.novko.api.response.SubcategoryResponse;
import com.novko.internal.categories.Subcategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubcategoryMapper {

    SubcategoryMapper INSTANCE = Mappers.getMapper(SubcategoryMapper.class);

    SubcategoryResponse toDto(Subcategory subcategory);
    Subcategory toEntity(SubcategoryResponse subcategoryResponse);

    List<SubcategoryResponse> listToDto(List<Subcategory> subcategories);
}
