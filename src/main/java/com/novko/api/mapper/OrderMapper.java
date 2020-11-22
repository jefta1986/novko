package com.novko.api.mapper;

import com.novko.api.response.OrderResponse;
import com.novko.internal.orders.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponse toDto(Order order);
    Order toEntity(OrderResponse orderResponse);

    List<OrderResponse> listToDto(List<Order> orders);
}
