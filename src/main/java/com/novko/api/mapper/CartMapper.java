package com.novko.api.mapper;

import com.novko.api.response.CartResponse;
import com.novko.internal.cart.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartResponse toDto(Cart cart);
    Cart toEntity(CartResponse cartResponse);

    List<CartResponse> listToDto(List<Cart> carts);

}
