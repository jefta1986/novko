package com.novko.api.mapper;

import com.novko.api.request.OrderStatusModelRequest;
import com.novko.api.response.OrderStatusModelResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface OrderStatusModelMapper {

    OrderStatusModelMapper INSTANCE = Mappers.getMapper(OrderStatusModelMapper.class);

    OrderStatusModelResponse toResponse(OrderStatusModelRequest orderStatusModelRequest);
    OrderStatusModelRequest toRequest(OrderStatusModelResponse orderStatusModelResponse);

}
