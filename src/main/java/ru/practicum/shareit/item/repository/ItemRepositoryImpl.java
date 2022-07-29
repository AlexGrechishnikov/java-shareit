package ru.practicum.shareit.item.repository;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.utill.exceptions.AlreadyExistsException;
import ru.practicum.shareit.utill.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private final List<Item> list = new ArrayList<>();
    private long counter = 1;

    @Override
    public Item create(Item item) {
        checkItem(item);
        item.setId(counter++);
        list.add(item);
        return item;
    }

    private void checkItem(Item item) {
        if (list.contains(item)) {
            throw new AlreadyExistsException(
                    String.format("Вещь с id = %d уже существует", item.getId()));
        }
    }

    @Override
    public Item findById(Long itemId) {
        return list.stream()
                .filter(i -> i.getId().equals(itemId))
                .findAny().orElseThrow(() -> {
                    throw new NotFoundException(
                            String.format("Вещь с id = %d не найдена", itemId));
                });
    }

    @Override
    public Item update(Item item) {
        list.remove(item);
        list.add(item);
        return item;
    }

    @Override
    public void deleteById(Long itemId) {

    }

    @Override
    public List<Item> findAll() {
        return List.copyOf(list);
    }

    @Override
    public List<Item> findAllByOwnerId(Long ownerId) {
        return list.stream()
                .filter(i -> i.getOwner().getId().equals(ownerId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> findAllByNameOrDescriptionPattern(String pattern) {
        String lowerCasePattern = pattern.toLowerCase();
        Set<Item> itemListByName = list.stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getName().toLowerCase().contains(lowerCasePattern))
                .collect(Collectors.toSet());
        Set<Item> itemListByDescription = list.stream()
                .filter(Item::getAvailable)
                .filter(item -> item.getDescription().toLowerCase().contains(lowerCasePattern))
                .collect(Collectors.toSet());
        itemListByName.addAll(itemListByDescription);
        return List.copyOf(itemListByName);
    }
}