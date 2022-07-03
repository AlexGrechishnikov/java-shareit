package ru.practicum.shareit.itemrequest;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemRequest {
    private Long id;
    private String description; // — текст запроса, содержащий описание требуемой вещи;
    private User requestor; // — пользователь, создавший запрос;
    private LocalDateTime created; // — дата и время создания запроса.
}