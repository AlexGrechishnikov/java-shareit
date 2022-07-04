package ru.practicum.shareit.itemrequest.service;

import ru.practicum.shareit.itemrequest.model.ItemRequest;

public interface ItemRequestService {

    ItemRequest create(ItemRequest itemRequest);

    ItemRequest findById(Long itemRequestId);

    ItemRequest update(ItemRequest itemRequest);

    void deleteBy(Long itemRequestId);
}