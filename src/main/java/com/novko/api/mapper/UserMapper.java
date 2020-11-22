package com.novko.api.mapper;

import com.novko.api.response.UserResponse;
import com.novko.security.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toDto(User user);
    User toEntity(UserResponse userResponse);

    List<UserResponse> listToDto(List<User> users);
}
