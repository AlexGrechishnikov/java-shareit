package ru.practicum.shareit.itemrequest.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.itemrequest.model.ItemRequest;
import ru.practicum.shareit.itemrequest.dto.ItemRequestDto;
import ru.practicum.shareit.utill.mapper.Mapper;

@Component
public class ItemRequestMapper implements Mapper<ItemRequest, ItemRequestDto> {
    @Override
    public ItemRequestDto toDto(ItemRequest entity) {
        return new ItemRequestDto(
                entity.getId(),
                entity.getDescription(),
                entity.getRequestor().getId(),
                entity.getCreated()
        );
    }

    @Override
    public ItemRequest toEntity(ItemRequestDto dto) {
        return null;
    }
}