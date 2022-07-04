package ru.practicum.shareit.user.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.utill.exceptions.AlreadyExistsException;
import ru.practicum.shareit.utill.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private final List<User> list = new ArrayList<>();
    private long counter = 1;

    @Override
    public User create(User user) {
        checkUser(user);
        user.setId(counter++);
        list.add(user);
        return user;
    }

    private void checkUser(User user) {
        if (list.contains(user)) {
            throw new AlreadyExistsException(
                    String.format("Пользователь с email = %s уже существует", user.getEmail()));
        }
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(list);
    }

    @Override
    public User findById(Long userId) {
        return list.stream()
                .filter(u -> u.getId().equals(userId))
                .findAny()
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("Пользователь с id = %d не найден", userId));
                });
    }

    @Override
    public User update(User user) {
        User forUpdateUser = findById(user.getId());
        checkUser(user);
        list.remove(forUpdateUser);
        if (user.getName() != null && !user.getName().isBlank()) {
            forUpdateUser.setName(user.getName());
        }
        if (user.getEmail() != null && !user.getEmail().isBlank()) {
            forUpdateUser.setEmail(user.getEmail());
        }
        list.add(forUpdateUser);
        return forUpdateUser;
    }

    @Override
    public void deleteById(Long userId) {
        list.remove(findById(userId));
    }
}