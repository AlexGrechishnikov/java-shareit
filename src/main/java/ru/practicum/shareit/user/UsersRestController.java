package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UsersRestController {

    private final UserMapper userMapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> postUsers(@RequestBody @Valid UserDto dto) {
        User user = userMapper.toEntity(dto);
        user = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDto(user));
    }

    @PutMapping
    public ResponseEntity<UserDto> putUsers(@RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        user = userService.update(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> dtos = new ArrayList<>();
        for (User u : users) {
            dtos.add(userMapper.toDto(u));
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUsers(@PathVariable Long userId) {
        return ResponseEntity.ok(userMapper.toDto(userService.findById(userId)));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUsers(@PathVariable Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.ok(String.format("Пользователь с id = %d удалён", userId));
    }
}