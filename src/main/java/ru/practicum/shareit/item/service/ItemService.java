package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item create(Item item);

    Item findById(Long itemId);

    Item update(Item item, Long ownerId);

    void deleteById(Long itemId, Long ownerId);

    List<Item> findAllByOwnerId(Long ownerId);

    List<Item> findAllByNameOrDescriptionPattern(String pattern);
}