package ru.practicum.shareit.item.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
import ru.practicum.shareit.utill.exceptions.AccessException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public Item create(Item item) {
        return itemRepository.create(item);
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public Item update(Item item, Long ownerId) {
        Item itemForUpdate = itemRepository.findById(item.getId());
        if (itemForUpdate.getOwner().getId().equals(ownerId)) {
            if (item.getAvailable() != null) {
                itemForUpdate.setAvailable(item.getAvailable());
            }
            if (item.getDescription() != null && !item.getDescription().isBlank()) {
                itemForUpdate.setDescription(item.getDescription());
            }
            if (item.getName() != null && !item.getName().isBlank()) {
                itemForUpdate.setName(item.getName());
            }
            itemRepository.update(itemForUpdate);
        } else {
            throw new AccessException("Нужно быть владельцем вещи что бы изменить или удалить");
        }
        return itemForUpdate;
    }

    @Override
    public void deleteById(Long itemId, Long ownerId) {
        Item item = itemRepository.findById(itemId);
        if (item.getOwner().getId().equals(ownerId)) {
            itemRepository.deleteById(itemId);
        } else {
            throw new AccessException("Нужно быть владельцем вещи что бы изменить или удалить");
        }
    }

    @Override
    public List<Item> findAllByOwnerId(Long ownerId) {
        return itemRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public List<Item> findAllByNameOrDescriptionPattern(String pattern) {
        return itemRepository.findAllByNameOrDescriptionPattern(pattern);
    }
}