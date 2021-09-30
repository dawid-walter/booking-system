package com.dwalter.bookingsystem.user.mapper;

import com.dwalter.bookingsystem.user.model.User;
import com.dwalter.bookingsystem.user.controller.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto mapToUserDto(User user);

    User mapToUser(UserDto userDto);

    List<UserDto> mapToUsersDto(List<User> users);
}
