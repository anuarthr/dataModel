package com.data.tallermodelodatos.dto;

import com.data.tallermodelodatos.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    User userDtoWithoutIdToUser(UserDto userDto);

    UserDto userToUserDto(User user);

    @Mapping(target = "id", ignore = true)
    UserDto userToUserDtoWithoutId(User user);
}