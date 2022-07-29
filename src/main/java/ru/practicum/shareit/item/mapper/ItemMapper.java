package ru.practicum.shareit.item.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.itemrequest.service.ItemRequestService;
import ru.practicum.shareit.user.service.UserService;
import ru.practicum.shareit.utill.mapper.Mapper;

@RequiredArgsConstructor
@Component
public class ItemMapper implements Mapper<Item, ItemDto> {

    private final UserService userService;
    private final ItemRequestService itemRequestService;

    @Override
    public ItemDto toDto(Item entity) {
        return new ItemDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getAvailable(),
                entity.getOwner().getId(),
                entity.getRequest() != null ? entity.getRequest().getId() : null
        );
    }

    @Override
    public Item toEntity(ItemDto dto) {
        return new Item(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getAvailable(),
                dto.getOwnerId() != null ? userService.findById(dto.getOwnerId()) : null,
                itemRequestService.findById(dto.getRequestId())
        );
    }
}