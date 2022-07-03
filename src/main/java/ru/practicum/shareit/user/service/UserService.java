package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.User;

import java.util.List;

public interface UserService {

    User create(User user);

    User findById(Long userId);

    User update(User user);

    void deleteById(Long id);

    List<User> findAll();
}