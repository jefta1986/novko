package com.novko.api.mapper;

import com.novko.api.response.OrderResponse;
import com.novko.internal.orders.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

//    @Mapping(target = "user", source = "order.user.username")
    OrderResponse toDto(Order order);

//    @Mapping(target = "user", source = "order.user.username")
//    Order toEntity(OrderResponse orderResponse);

    List<OrderResponse> listToDto(List<Order> orders);

    default Page<OrderResponse> pageToDto(Page<Order> orders) {
        return orders.map(e -> toDto(e));
    }

}
