package ru.practicum.shareit.item.repository;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemRepository {

    Item create(Item item);

    Item findById(Long itemId);

    Item update(Item item);

    void deleteById(Long itemId);

    List<Item> findAll();

    List<Item> findAllByOwnerId(Long ownerId);

    List<Item> findAllByNameOrDescriptionPattern(String pattern);
}