package ru.practicum.shareit.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.service.ItemService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemsRestController {

    private final ItemMapper itemMapper;
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDto> postItems(@RequestHeader("X-Sharer-User-Id") Long requestOwnerId,
                                             @RequestBody @Valid ItemDto dto) {
        dto.setOwnerId(requestOwnerId);
        Item item = itemMapper.toEntity(dto);
        item = itemService.create(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.toDto(item));
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<ItemDto> patchItems(@RequestHeader("X-Sharer-User-Id") Long requestOwnerId,
                                              @RequestBody ItemDto dto,
                                              @PathVariable Long itemId) {
        dto.setId(itemId);
        Item item = itemMapper.toEntity(dto);
        item = itemService.update(item, requestOwnerId);
        return ResponseEntity.ok(itemMapper.toDto(item));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItemsById(@PathVariable Long itemId) {
        return ResponseEntity.ok(itemMapper.toDto(itemService.findById(itemId)));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItemsByOwnerId(@RequestHeader("X-Sharer-User-Id") Long requestOwnerId) {
        return ResponseEntity.ok(itemMapper.toDtoList(itemService.findAllByOwnerId(requestOwnerId)));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> getItemsBySearchParam(@RequestParam("text") String pattern) {
        return ResponseEntity.ok(itemMapper.toDtoList(itemService.findAllByNameOrDescriptionPattern(pattern)));
    }
}