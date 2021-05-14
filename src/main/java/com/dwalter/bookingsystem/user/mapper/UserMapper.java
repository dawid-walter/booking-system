package com.dwalter.bookingsystem.user.mapper;

import com.dwalter.bookingsystem.user.domain.User;
import com.dwalter.bookingsystem.user.domain.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

    List<UserDto> mapToUsersDto(List<User> users);
}
