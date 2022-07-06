package ru.practicum.shareit.booking.model;

//TODO класс будет реализован в следующих спринтах

public enum BookingStatus {
    WAITING,  // — новое бронирование, ожидает одобрения,
    APPROVED, // — бронирование подтверждено владельцем,
    REJECTED, // — бронирование отклонено владельцем,
    CANCELED  // — бронирование отменено создателем.
}