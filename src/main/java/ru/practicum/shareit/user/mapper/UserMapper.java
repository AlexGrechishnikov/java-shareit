package ru.practicum.shareit.user.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.utill.mapper.Mapper;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public UserDto toDto(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail()
        );
    }

    @Override
    public User toEntity(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getEmail()
        );
    }
}