package ru.practicum.shareit.booking;

import lombok.Getter;
import lombok.Setter;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;

import java.time.LocalDateTime;

@Getter
@Setter
public class Booking {
    private Long id;
    private LocalDateTime start; // — дата начала бронирования;
    private LocalDateTime end; // — дата конца бронирования;
    private Item item; // — вещь, которую пользователь бронирует;
    private User booker; // — пользователь, который осуществляет бронирование;
    private BookingStatus status; // — статус бронирования. Может принимать одно из следующих значений:
}
